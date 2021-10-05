package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.ColorDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateColorRequest;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapper modelMapper;

	@Autowired
	public ColorManager(ColorDao colorDao,ModelMapper modelMapper) {
		super();
		this.colorDao = colorDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public DataResult<List<Color>> getAll() {
		
		return new SuccessDataResult<List<Color>>(this.colorDao.findAll(), Messages.ColorsListed);
	}

	@Override
	public DataResult<Color> getById(int colorId) {
		
		return new SuccessDataResult<Color>(this.colorDao.getById(colorId), Messages.ColorListed);
	}
	
	@Override
	public DataResult<List<ColorDetailDto>> getColorsDetail() {
		
		List<Color> colors = this.colorDao.findAll();
		
		List<ColorDetailDto> colorDetailDto = colors.stream().map(color -> modelMapper.map(color, ColorDetailDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorDetailDto>>(colorDetailDto, Messages.ColorsListed);
	}

	@Override
	public DataResult<ColorDetailDto> getColorDetailId(int colorId) {
		
		Color color = this.colorDao.getById(colorId);
		
		return new SuccessDataResult<ColorDetailDto>(modelMapper.map(color, ColorDetailDto.class), Messages.ColorListed);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {

		var result = BusinessRules.run(this.checkColorByColorName(createColorRequest.getColorName()));

		if (result != null) {
			return result;
		}
		
		Color color = new Color();
		color.setColorName(createColorRequest.getColorName());

		this.colorDao.save(color);
		return new SuccessResult(Messages.ColorAdded);

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {

		var result = BusinessRules.run(this.checkColorByColorName(updateColorRequest.getColorName()));

		if (result != null) {
			return result;
		}
		
		Color color = this.colorDao.getById(updateColorRequest.getColorId());
		color.setColorName(updateColorRequest.getColorName());

		this.colorDao.save(color);
		return new SuccessResult(Messages.ColorUpdated);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {

		Color color = this.colorDao.getById(deleteColorRequest.getColorId());

		this.colorDao.delete(color);
		return new SuccessResult(Messages.ColorDeleted);
	}
	
	private Result checkColorByColorName(String colorName) {
		
		if (this.colorDao.existsByColorName(colorName)) {
			return new ErrorResult(Messages.ColorIsFound);
		}
		return new SuccessResult();
	}

}
