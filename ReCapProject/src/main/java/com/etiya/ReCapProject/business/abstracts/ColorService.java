package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.requests.create.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateColorRequest;

public interface ColorService {
	
	DataResult<List<Color>> getAll();

	DataResult<Color> getById(int colorId);

	Result add(CreateColorRequest createColorRequest);

	Result update(UpdateColorRequest updateColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);

}
