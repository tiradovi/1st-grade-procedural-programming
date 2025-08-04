package presentation;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import control.CLogin;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class PLogin {
	
	public VUserInfo login(Scanner keyboard) {
		int maxAttempts = 5;
		int lockoutDuration = 30;
		int attempts = 0;
		System.out.println("�α����� ���ּ���");
		

		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ����");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		if (choice == 1) {
			System.out.println("����� ���̵� �Է�");
			String userId = keyboard.next();
			System.out.println("��й�ȣ �Է�");
			String password = keyboard.next();

			VLogin vLogin = new VLogin();
			vLogin.setUserId(userId);
			vLogin.setPassword(password);

			CLogin cLogin = new CLogin();
			VUserInfo vUserInfo = cLogin.login(vLogin);

			if (vUserInfo == null) {
				attempts++;
				if (attempts >= maxAttempts) {
					System.out.println("��й�ȣ�� 5ȸ �̻� �߸� �Է��Ͽ� " + lockoutDuration + "�ʰ� �Է��� �����˴ϴ�.");
					try {
						TimeUnit.SECONDS.sleep(30);
						this.login(keyboard);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				} else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�");
					this.login(keyboard);
				}
			} else {
				System.out.println(vUserInfo.getName() + "�� �ȳ��ϼ���.");
			}

			return vUserInfo;

		} else if (choice == 2) {
			int minUserIdLength = 4;
			int maxUserIdLength = 8;
			int minPasswordLength = 3;
			int maxPasswordLength = 10;

			System.out.println("ȸ�������� �����մϴ�.");
			System.out.println("������ ����� ���̵� �Է����ּ���");
			System.out.println("���̵�� " + minUserIdLength + "�� �̻�, " + maxUserIdLength + "�� ���Ͽ��� �մϴ�.");
			String userId = keyboard.next();
			System.out.println("������ ��й�ȣ�� �Է����ּ���");
			System.out.println("��й�ȣ�� " + minPasswordLength + "�� �̻�, " + maxPasswordLength + "�� ���Ͽ��� �մϴ�.");
			String password = keyboard.next();
			System.out.println("������� �̸��� �Է����ּ���");
			String name = keyboard.next();

			if (userId.length() < minUserIdLength || userId.length() > maxUserIdLength) {
				System.out.println("���̵� " + minUserIdLength + "�� �̻�, " + maxUserIdLength + "�� ���ϰ� �ƴմϴ�. �������� ���ư��ϴ�.");
				this.login(keyboard);
			}
			if (password.length() < minPasswordLength || password.length() > maxPasswordLength) {
				System.out.println(
						"��й�ȣ�� " + minPasswordLength + "�� �̻�, " + maxPasswordLength + "�� ���ϰ� �ƴմϴ�. �������� ���ư��ϴ�.");
				this.login(keyboard);
			}

			VLogin vLogin = new VLogin();
			vLogin.setUserId(userId);
			vLogin.setPassword(password);

			CLogin cLogin = new CLogin();
			cLogin.createAccount(vLogin, name);

			return null;
		}else if (choice == 3) {
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0);
		}else {
			System.out.println("������ ���ڸ� �Է����ּ���.");
			this.login(keyboard);
		}

		return null;
	}
}
