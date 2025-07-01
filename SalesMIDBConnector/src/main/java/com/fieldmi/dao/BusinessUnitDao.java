package com.fieldmi.dao;

import com.fieldmi.stubs.WsBusinessUnitOutput;
import com.softtantra.ws.Credentials;

public interface BusinessUnitDao {

	WsBusinessUnitOutput getBusinessUnitList(Credentials cre) throws Exception;

}
