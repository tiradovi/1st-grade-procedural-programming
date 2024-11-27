package Main;

import java.util.Scanner;

import presentation.PLogin;
import presentation.PSugangsincheong;
import valueObject.VUserInfo;

public class Main {
	private Scanner keyboard;
	private PLogin pLogin;

	public Main() {
	}

	public void run() {
		keyboard = new Scanner(System.in);
		pLogin = new PLogin();
		VUserInfo vUserInfo = pLogin.login(keyboard);
		if (vUserInfo != null) {
			PSugangsincheong pSugangsincheong = new PSugangsincheong();
			pSugangsincheong.run(vUserInfo, keyboard);
		}
		keyboard.close();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
}
