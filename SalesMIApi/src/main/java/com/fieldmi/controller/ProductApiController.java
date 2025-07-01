package com.fieldmi.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fieldmi.FieldMILogger;
import com.fieldmi.ProductImportRequest;
import com.fieldmi.service.FMProductService;
import com.fieldmi.stubs.WsProductDetails;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.PriceMasterImportRecords;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.Tax;

@RestController
public class ProductApiController {

	@Autowired
	private FMProductService fmProductService;

	@RequestMapping(value = "importProducts", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity importProducts(@RequestBody String productImportString) throws Exception {

		if (productImportString != null && productImportString.length() > 0) {

			
			// Start of parsing input data
			JSONObject json = new JSONObject(productImportString);
			ProductImportRequest productImportRequest = new ProductImportRequest();
			JSONArray itemDetails = json.getJSONArray("ItemDetails");
			for (Object itemDetailObj : itemDetails) {

				WsProductDetails wsProductDetails = new WsProductDetails();
				wsProductDetails.setProductDetails(null);
				if (itemDetailObj instanceof JSONObject) {					
									
					JSONObject itemDetail = (JSONObject) itemDetailObj;
					String productDescription = itemDetail.getString("ProductDescription");
					String productCode = itemDetail.getString("ProductCode");
					String brand_id1 = itemDetail.getString("brand_id1");
					String displaypackname = itemDetail.getString("displaypackname");
					String itemcode = itemDetail.getString("itemcode");
					String pack = itemDetail.getString("pack");
					String packUnit = itemDetail.getString("packUnit");					
					String nettwt = itemDetail.getString("NettWt");
					String msp = itemDetail.getString("msp");
					String mrp = itemDetail.getString("mrp");
					String tax_key = itemDetail.getString("tax_key");
					String rate = itemDetail.getString("rate");
					String hsncode = itemDetail.getString("hsncode");
					String company_id = itemDetail.getString("company_id");
					String created_by = itemDetail.getString("created_by");
					
					String barcode = itemDetail.getString("barcode");
					String category_name = itemDetail.getString("category_id1");
					String sub_category_name = itemDetail.getString("sub_category1");

					StringBuilder weightUnit = new StringBuilder();
					StringBuilder weight = new StringBuilder();
					if( nettwt != null && nettwt.trim().length() > 0 ) {
						
						for (int len=0; len < nettwt.length(); len++) {
						
							Boolean flag = Character.isDigit(nettwt.charAt(len));
					         if(flag) {
					        	 weight.append(nettwt.charAt(len)); 
					         }
					         else {
					        	 weightUnit.append(nettwt.charAt(len));
					         }							
						}
						
					}
					
					NewPriceMaster packDetails = new NewPriceMaster();
					packDetails.setMsp(Double.parseDouble(msp));
					packDetails.setMrp(Double.parseDouble(mrp));
					packDetails.setRate(Double.parseDouble(rate));
					packDetails.setItemcode(itemcode);
					packDetails.setPack(pack);
					packDetails.setPackunit(packUnit);
					packDetails.setTax_key(tax_key);
					packDetails.setPieceWeightUnit(weightUnit.toString());
					packDetails.setCompany_id(Integer.parseInt(company_id));
					packDetails.setCreated_by(Integer.parseInt(created_by));
					packDetails.setProduct_code(productCode);
					packDetails.setProductName(productDescription);
					packDetails.setDisplaypackname(displaypackname);
					if( category_name != null )	
						packDetails.setCategory_name(category_name);	
					else
						packDetails.setCategory_name("COLOUR SPLASH");
					
					if( sub_category_name != null )	
						packDetails.setSub_category_name(sub_category_name);
					else
						packDetails.setSub_category_name("ASSORTED");
					
					packDetails.setBrand_name(brand_id1);
					packDetails.setHsncode(hsncode);
					if(barcode!=null )
						packDetails.setBarcode(barcode);
					else
						packDetails.setBarcode(hsncode);
					
					packDetails.setUomSize(1);
					packDetails.setDivision("NA");
					
					wsProductDetails.getPriceMasterList().add(packDetails);
				}
				
				productImportRequest.getProductsToImport().add(wsProductDetails);
			}
			// end of parsing input data

			if (productImportRequest.getProductsToImport() != null
					&& productImportRequest.getProductsToImport().size() <= 9999) {

				List<String> importMessagesList = new ArrayList<String>();
				try {

					List<WsProductDetails> productListToImport = productImportRequest.getProductsToImport();

					List<Tax> taxList = null;
					for (WsProductDetails wsProductDetails : productListToImport) {

						StringBuilder importMessage = new StringBuilder();
						ProductDetails productMaster = wsProductDetails.getProductDetails();						

						if (taxList == null) {

							taxList = fmProductService.getTaxList(productMaster.getCompany_id());
						}

						if (productMaster == null) {

							importPackAsProduct(wsProductDetails, taxList, importMessage);
						} else {
							importProductAndPack(wsProductDetails, taxList, importMessage);
						}

						importMessagesList.add(importMessage.toString());
					}
				} catch (Exception e) {

					FieldMILogger.error(this.getClass().getName(), e);
				}

				return new ResponseEntity<List<String>>(importMessagesList, HttpStatus.OK);

			} else {

				return new ResponseEntity<String>(
						"Product Import failed due to size of customers to import is more than 9999",
						HttpStatus.BAD_REQUEST);
			}
		} else {

			return new ResponseEntity<String>(
					"Product Import failed due to invalid request OR Check the request format or size of customers to import is less than 20",
					HttpStatus.BAD_REQUEST);
		}
	}

	private void importProductAndPack(WsProductDetails wsProductDetails, List<Tax> taxList,
			StringBuilder importMessage) {

		// mandatory attributes: product_code, name, brand, category_id, sub_category,
		// barCode, hsncode
		Date date = Calendar.getInstance().getTime();
		ProductDetails productMaster = wsProductDetails.getProductDetails();
		if (productMaster.getProduct_code() != null && productMaster.getProduct_code().trim().length() > 0
				&& productMaster.getName() != null && productMaster.getName().trim().length() > 0
				&& productMaster.getCategory_id1() != null && productMaster.getCategory_id1().trim().length() > 0
				&& productMaster.getSub_category1() != null && productMaster.getSub_category1().trim().length() > 0
				&& productMaster.getBrand_id1() != null && productMaster.getBrand_id1().trim().length() > 0) {

			productMaster.setCreated_date(date);
			productMaster.setUpdated_by(productMaster.getCreated_by());
			productMaster.setUpdated_date(date);
			productMaster.setProductListSync(1);
			productMaster.setStatus(1);

			ProductDetails product = null;
			List<ProductDetails> products = fmProductService.getProductDetails(productMaster.getProduct_code(),
					productMaster.getCompany_id());
			boolean isProductNew = false;
			if (products == null || products.size() == 0) {

				isProductNew = true;
				productMaster.setStatus(1);
				product = fmProductService.addNewProduct(productMaster, productMaster.getCompany_id(),
						productMaster.getCreated_by());

			} else if (products.size() > 0) {

				product = products.get(0);
				product.setCategory_id1(productMaster.getCategory_id1());
				product.setSub_category1(productMaster.getSub_category1());
				product.setUpdated_by(productMaster.getCreated_by());
				product.setBrand_id1(productMaster.getBrand_id1());
				product.setHsncode(productMaster.getHsncode());
				product.setBarCode(productMaster.getBarCode());
				product.setName(productMaster.getName());
				product.setUomSize(productMaster.getUomSize());
				product.setDivision(productMaster.getDivision());
				product.setStatus(1);

				product = fmProductService.addNewProduct(product, productMaster.getCompany_id(),
						productMaster.getCreated_by());
			}

			if (product != null) {

				// find if packdetails exists, if yes update pack details
				// mandatory attributes: itemCode, pack, packUnit, mrp, rate, tax, msp,
				// pieceQty, pieceWeightUnit,
				// pieceWeight, pieceMrp

				List<NewPriceMaster> packDetails = wsProductDetails.getPriceMasterList();
				List<NewPriceMaster> existingPackDetails = null;

				if (!isProductNew) {

					existingPackDetails = fmProductService.getPriceDetailsForProduct(product.getProduct_id());
				}
				for (NewPriceMaster newPackDetail : packDetails) {

					if (newPackDetail.getItemcode() != null && newPackDetail.getItemcode().trim().length() > 0
							&& newPackDetail.getPack() != null && newPackDetail.getPack().trim().length() > 0
							&& newPackDetail.getPackunit() != null && newPackDetail.getPackunit().trim().length() > 0
							&& newPackDetail.getTax_key() != null && newPackDetail.getTax_key().trim().length() > 0
							&& newPackDetail.getPieceWeightUnit() != null
							&& newPackDetail.getPieceWeightUnit().trim().length() > 0) {

						int tax_id = 0;
						if (taxList != null) {
							for (Tax tax : taxList) {

								if (tax.getName() != null && tax.getName().trim().toUpperCase()
										.equals(newPackDetail.getTax_key().trim().toUpperCase())) {

									tax_id = tax.getManage_tax_id();
									break;
								} else if (tax.getCode() != null && tax.getCode().trim().toUpperCase()
										.equals(newPackDetail.getTax_key().trim().toUpperCase())) {

									tax_id = tax.getManage_tax_id();
									break;
								}
							}
						}

						NewPriceMaster existingPackDetail = null;
						if (existingPackDetails != null && existingPackDetails.size() > 0) {

							for (NewPriceMaster loopPackDetails : existingPackDetails) {

								if (loopPackDetails.getItemcode().toUpperCase().equals(newPackDetail.getItemcode())) {

									existingPackDetail = loopPackDetails;
									break;
								}
							}
						}

						if (existingPackDetail == null) {

							newPackDetail.setUpdated_by(newPackDetail.getCreated_by());
							newPackDetail.setStatus(1);
							newPackDetail.setUpdated_date(date);
							newPackDetail.setCreated_date(date);
							newPackDetail.setProduct_id(product.getProduct_id());
							newPackDetail.setProduct_code(product.getProduct_code());
							newPackDetail.setPacktitle(newPackDetail.getDisplaypackname());
							newPackDetail.setMrp(newPackDetail.getMsp());
							newPackDetail.setProductName(product.getName());
							newPackDetail.setTax(tax_id);
							newPackDetail.setActivestatus(1);

							existingPackDetail = fmProductService.addNewPriceMaster(newPackDetail,
									newPackDetail.getCompany_id(), newPackDetail.getCreated_by());
						} else {

							existingPackDetail.setPack(newPackDetail.getPack());
							existingPackDetail.setPackunit(newPackDetail.getPackunit());
							existingPackDetail.setMrp(newPackDetail.getMrp());
							existingPackDetail.setRate(newPackDetail.getRate());
							existingPackDetail.setMsp(newPackDetail.getMsp());
							existingPackDetail.setMrp(newPackDetail.getMsp());
							existingPackDetail.setPiece_qty(newPackDetail.getPiece_qty());
							existingPackDetail.setPieceWeight(newPackDetail.getPieceWeight());
							existingPackDetail.setPieceWeightUnit(newPackDetail.getPieceWeightUnit());
							existingPackDetail.setPiece_mrp(newPackDetail.getPiece_mrp());
							existingPackDetail.setUpdated_by(newPackDetail.getCreated_by());
							existingPackDetail.setProduct_code(product.getProduct_code());
							existingPackDetail.setProductName(newPackDetail.getProductName());
							existingPackDetail.setPacktitle(newPackDetail.getDisplaypackname());
							existingPackDetail.setDisplaypackname(newPackDetail.getDisplaypackname());
							existingPackDetail.setPiece_pack(newPackDetail.getPiece_pack());
							existingPackDetail.setPiece_unit(newPackDetail.getPiece_unit());
							existingPackDetail.setUpdated_date(date);
							existingPackDetail.setTax(tax_id);
							existingPackDetail.setStatus(1);
							existingPackDetail.setActivestatus(1);

							existingPackDetail = fmProductService.addNewPriceMaster(existingPackDetail,
									newPackDetail.getCompany_id(), newPackDetail.getCreated_by());
						}
					} else {

						importMessage.append("Mandatory fields not available for pack details with code:"
								+ newPackDetail.getItemcode());
					}
				}
			}

		} else {

			importMessage.append("Mandatory fields not available for product code:" + productMaster.getProduct_code());
		}

		if (importMessage.length() == 0) {

			importMessage.append("Product Imported successfully with product code: " + productMaster.getProduct_code());
		}

	}

	private void importPackAsProduct(WsProductDetails wsProductDetails, List<Tax> taxList,
			StringBuilder importMessage) {

		Date date = Calendar.getInstance().getTime();
		List<NewPriceMaster> packDetails = wsProductDetails.getPriceMasterList();
		for (NewPriceMaster newPackDetail : packDetails) {

			if (newPackDetail.getProduct_code() != null && newPackDetail.getProduct_code().trim().length() > 0
					&& newPackDetail.getDisplaypackname() != null
					&& newPackDetail.getDisplaypackname().trim().length() > 0
					&& newPackDetail.getCategory_name() != null && newPackDetail.getCategory_name().trim().length() > 0
					&& newPackDetail.getSub_category_name() != null
					&& newPackDetail.getSub_category_name().trim().length() > 0 && newPackDetail.getBrand_name() != null
					&& newPackDetail.getBrand_name().trim().length() > 0) {

				List<ProductDetails> products = fmProductService.getProductDetails(newPackDetail.getProduct_code(),
						newPackDetail.getCompany_id());
				boolean isProductNew = false;
				ProductDetails product = null;
				if (products == null || products.size() == 0) {

					isProductNew = true;
					product = new ProductDetails();
					product.setCreated_by(newPackDetail.getCreated_by());
				} else if (products.size() > 0) {

					product = products.get(0);
				}

				product.setCompany_id(newPackDetail.getCompany_id());
				product.setUpdated_by(newPackDetail.getCreated_by());
				product.setProduct_code(newPackDetail.getProduct_code());
				product.setName(newPackDetail.getDisplaypackname());
				product.setCategory_id1(newPackDetail.getCategory_name());
				product.setSub_category1(newPackDetail.getSub_category_name());
				product.setUpdated_by(newPackDetail.getCreated_by());
				product.setBrand_id1(newPackDetail.getBrand_name());
				product.setHsncode(newPackDetail.getHsncode());
				product.setBarCode(newPackDetail.getBarcode());
				product.setUomSize(newPackDetail.getUomSize());
				product.setDivision(newPackDetail.getDivision());
				product.setStatus(1);

				product = fmProductService.addNewProduct(product, product.getCompany_id(), product.getCreated_by());

				List<NewPriceMaster> existingPackDetails = null;

				if (!isProductNew) {

					existingPackDetails = fmProductService.getPriceDetailsForProduct(product.getProduct_id());
				}

				if (newPackDetail.getItemcode() != null && newPackDetail.getItemcode().trim().length() > 0
						&& newPackDetail.getPack() != null && newPackDetail.getPack().trim().length() > 0
						&& newPackDetail.getPackunit() != null && newPackDetail.getPackunit().trim().length() > 0
						&& newPackDetail.getTax_key() != null && newPackDetail.getTax_key().trim().length() > 0
						&& newPackDetail.getPieceWeightUnit() != null
						&& newPackDetail.getPieceWeightUnit().trim().length() > 0) {

					int tax_id = 0;
					if (taxList != null) {
						for (Tax tax : taxList) {

							if (tax.getName() != null && tax.getName().trim().toUpperCase()
									.equals(newPackDetail.getTax_key().trim().toUpperCase())) {

								tax_id = tax.getManage_tax_id();
								break;
							} else if (tax.getCode() != null && tax.getCode().trim().toUpperCase()
									.equals(newPackDetail.getTax_key().trim().toUpperCase())) {

								tax_id = tax.getManage_tax_id();
								break;
							}
						}
					}

					NewPriceMaster existingPackDetail = null;
					if (existingPackDetails != null && existingPackDetails.size() > 0) {

						for (NewPriceMaster loopPackDetails : existingPackDetails) {

							if (loopPackDetails.getItemcode().toUpperCase().equals(newPackDetail.getItemcode())) {

								existingPackDetail = loopPackDetails;
								break;
							}
						}
					}

					if (existingPackDetail == null) {

						newPackDetail.setUpdated_by(newPackDetail.getCreated_by());
						newPackDetail.setStatus(1);
						newPackDetail.setUpdated_date(date);
						newPackDetail.setCreated_date(date);
						newPackDetail.setProduct_id(product.getProduct_id());
						newPackDetail.setProduct_code(product.getProduct_code());
						newPackDetail.setPacktitle(newPackDetail.getDisplaypackname());
						newPackDetail.setMrp(newPackDetail.getMsp());
						newPackDetail.setProductName(product.getName());
						newPackDetail.setTax(tax_id);
						newPackDetail.setActivestatus(1);

						existingPackDetail = fmProductService.addNewPriceMaster(newPackDetail,
								newPackDetail.getCompany_id(), newPackDetail.getCreated_by());
					} else {

						existingPackDetail.setPack(newPackDetail.getPack());
						existingPackDetail.setPackunit(newPackDetail.getPackunit());
						existingPackDetail.setMrp(newPackDetail.getMrp());
						existingPackDetail.setRate(newPackDetail.getRate());
						existingPackDetail.setMsp(newPackDetail.getMsp());
						existingPackDetail.setMrp(newPackDetail.getMsp());
						existingPackDetail.setPiece_qty(newPackDetail.getPiece_qty());
						existingPackDetail.setPieceWeight(newPackDetail.getPieceWeight());
						existingPackDetail.setPieceWeightUnit(newPackDetail.getPieceWeightUnit());
						existingPackDetail.setPiece_mrp(newPackDetail.getPiece_mrp());
						existingPackDetail.setUpdated_by(newPackDetail.getCreated_by());
						existingPackDetail.setProduct_code(product.getProduct_code());
						existingPackDetail.setProductName(newPackDetail.getProductName());
						existingPackDetail.setPacktitle(newPackDetail.getDisplaypackname());
						existingPackDetail.setDisplaypackname(newPackDetail.getDisplaypackname());
						existingPackDetail.setPiece_pack(newPackDetail.getPiece_pack());
						existingPackDetail.setPiece_unit(newPackDetail.getPiece_unit());
						existingPackDetail.setUpdated_date(date);
						existingPackDetail.setTax(tax_id);
						existingPackDetail.setStatus(1);
						existingPackDetail.setActivestatus(1);

						existingPackDetail = fmProductService.addNewPriceMaster(existingPackDetail,
								newPackDetail.getCompany_id(), newPackDetail.getCreated_by());
					}
				} else {

					importMessage.append(
							"Mandatory fields not available for pack details with code:" + newPackDetail.getItemcode());
				}
			}

			if (importMessage.length() == 0) {

				importMessage
						.append("Product Imported successfully with product code: " + newPackDetail.getProduct_code());
			}
		}
	}

}
