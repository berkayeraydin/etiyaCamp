package com.etiya.ReCapProject.business.abstracts;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

	ModelMapper forDto();

	ModelMapper forRequest();

}
