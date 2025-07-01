package com.fieldmi.service;

import com.fieldmi.stubs.WsBusinessUnitOutput;
import com.softtantra.ws.Credentials;

public interface BusinessUnitService {

	WsBusinessUnitOutput getBusinessUnitList(Credentials cre) throws Exception;
}
