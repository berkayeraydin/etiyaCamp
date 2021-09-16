package com.etiya.ReCapProject.core.utilities.businnes;

import com.etiya.ReCapProject.core.results.Result;

public class BusinnesRules {
	
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
