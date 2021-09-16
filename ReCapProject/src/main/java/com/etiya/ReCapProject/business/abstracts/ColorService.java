package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Color;

public interface ColorService {
	
	DataResult<List<Color>>getAll();
	
	DataResult<Color> getById(int colorId);
	
	Result add(Color color);
	
	Result update(Color color);
	
	Result delete(Color color);

}
