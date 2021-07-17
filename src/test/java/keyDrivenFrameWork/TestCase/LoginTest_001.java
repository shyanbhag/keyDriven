package keyDrivenFrameWork.TestCase;

import org.testng.annotations.Test;

import keyDrivenFrameWork.utilities.KeyDrivenEngine;

public class LoginTest_001 {
	
	public KeyDrivenEngine key;
	@Test(priority=1)
	public void LoginTest() {	
		key=new KeyDrivenEngine();
		key.startRead("login");
	}
	@Test(priority=2)
	public void addAccountTest() {	
		key=new KeyDrivenEngine();
		key.startRead("addAccount");
	}

}
