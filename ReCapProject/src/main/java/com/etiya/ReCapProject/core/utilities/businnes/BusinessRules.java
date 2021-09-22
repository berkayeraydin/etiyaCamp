package com.etiya.ReCapProject.core.utilities.businnes;

import com.etiya.ReCapProject.core.utilities.result.Result;

public class BusinessRules {
	
	public static Result run (Result... logics)
	{
		for (Result  logic : logics)  {
			
			if(!logic.isSuccess() )
			{
				return logic;
			}
			
		}
		
		return null;
	}
}
