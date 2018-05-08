package me.postmus.joris;

public class Decryptor {
	
	public char getChar(int number) {
		if (number > 1) {
			int curChar = number / 9;
			//Loop through all letters of alphabet
			if(curChar == 10) {return 'a';} else
			if(curChar == 11) {return 'b';} else
			if(curChar == 12) {return 'c';} else
			if(curChar == 13) {return 'd';} else
			if(curChar == 14) {return 'e';} else
			if(curChar == 15) {return 'f';} else
			if(curChar == 16) {return 'g';} else
			if(curChar == 17) {return 'h';} else
			if(curChar == 18) {return 'i';} else
			if(curChar == 19) {return 'j';} else
			if(curChar == 20) {return 'k';} else
			if(curChar == 21) {return 'l';} else
			if(curChar == 22) {return 'm';} else
			if(curChar == 23) {return 'n';} else
			if(curChar == 24) {return 'o';} else
			if(curChar == 25) {return 'p';} else
			if(curChar == 26) {return 'q';} else
			if(curChar == 27) {return 'r';} else
			if(curChar == 28) {return 's';} else
			if(curChar == 29) {return 't';} else
			if(curChar == 30) {return 'u';} else
			if(curChar == 31) {return 'v';} else
			if(curChar == 32) {return 'w';} else
			if(curChar == 33) {return 'x';} else
			if(curChar == 34) {return 'y';} else
			if(curChar == 35) {return 'z';} else
			{return ' ';}
		}else {return ' ';}
	}
}
