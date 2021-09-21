package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.requests.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.UpdateColorRequest;

public interface ColorService {
	
	DataResult<List<Color>>getAll();
	
	DataResult<Color> getById(int colorId);
	
	Result add(CreateColorRequest createColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);
	
	Result delete(DeleteColorRequest deleteColorRequest);

}
