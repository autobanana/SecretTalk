package com.example.secrettalk5;

import net.emome.hamiapps.sdk.ForwardActivity;

public class CheckLicense extends ForwardActivity
{
	@Override
	public Class getTargetActivity() 
	{
		return Begin_Prefence_Register_Anim.class;
	}
	
	@Override
	public boolean passOnUnavailableDataNetwork()
	{
		return true;
	}
}
