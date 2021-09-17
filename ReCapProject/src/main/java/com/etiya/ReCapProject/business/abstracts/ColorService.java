package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.requests.CreateColorRequest;

public interface ColorService {
	
	DataResult<List<Color>>getAll();
	
	DataResult<Color> getById(int colorId);
	
	Result add(CreateColorRequest createColorRequest);
	
	Result update(CreateColorRequest createColorRequest);
	
	Result delete(int colorId);

}
