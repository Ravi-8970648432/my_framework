package practice;

import java.util.Random;

import java_cup.runtime.Symbol;

public class RandNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random=new Random();
		int randNum=random.nextInt(1000);
		System.out.println(randNum);

	}

}
