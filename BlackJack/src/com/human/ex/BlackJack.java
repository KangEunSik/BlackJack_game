package com.human.ex;
import java.util.Scanner;
class BlakcJectEx{
	public static Scanner sc =new Scanner(System.in);
	public int deck[] = new int[52]; //카드 전체 묶음
	public  int p1Deck[] = new int[10];
	public  int p2Deck[] = new int[10];
	public  int deckIndex=52; //전체 카드중에 사용유무 구분 인덱스
	public int p1DeckIndex=0;//p1이 가지고 있는 카드 개수
	public  int p2DeckIndex=0;//p2이 가지고 있는 카드 개수
	public static boolean isP1GEnd=false;//p1이 카드를 계속 받을건지 결정
	public static boolean isP2GEnd=false;//p2게임을 종료할건지 결정
	
	//카드 모양이름을 정의한 배열
	public static final String CARD_SHAPE[]= {"스페이드","다이아","하트","클로버"};
	public static final String CARD_NUMBER[]=
		{"A","2","3","4","5","6","7","8","9","10","j","Q","K"};
	public  void newCard() {//카드 초기화
		for(int i=0;i<deck.length;i++) {
			deck[i]=i;
		}
		deckIndex=52; p1DeckIndex=0; p2DeckIndex=0;
	}
	public  void mixCard() {//카드 섞기
		for(int i=0; i<10000; i++) {
			int randomIndex=(int)(Math.random()*52);
			int temp=deck[0];
			deck[0]=deck[randomIndex];
			deck[randomIndex]=temp;
		}
	}
	public  void newGame() {
		isP1GEnd=false; 
		isP2GEnd=false;
	}
	public  void displayDeckCard() {
		for(int i=0;i<deck.length;i++) {
			//모양을 찍으려면 카드 번호에서 13으로 나눈 값을 CARD_SHAPE의 인덱스로 사용한다.
		
		}
	}
	public  void displayUsingDeck() { //사용한 deck 카드 출력
		System.out.println("\n displayUsingDeck...");
		for(int i=deckIndex;i<deck.length;i++) {
			System.out.print(CARD_SHAPE[deck[i]/13]+" "+CARD_NUMBER[deck[i]%13]+" ");
		}
	}
	public  void displayNoUsingDeck() {//사용하지 않은 dec카드 출력
		System.out.println("\n displayNoUsingDeck...");
		for(int i=0;i<deckIndex;i++) {
			System.out.print(CARD_SHAPE[deck[i]/13]+" "+CARD_NUMBER[deck[i]%13]+" ");
		}
	}
	//p1,p2가 가지고 있는 카드를 출력하는 메소드 구현
	public void displayP1Deck() {
		
		for(int i=0;i<p1DeckIndex;i++) {
			System.out.print(CARD_SHAPE[p1Deck[i]/13]+" "
					+CARD_NUMBER[p1Deck[i]%13]+" ");
			
		}
	}
	public  void displayP2Deck() {
		
		for(int i=0;i<p2DeckIndex;i++) {
			System.out.print(CARD_SHAPE[p2Deck[i]/13]+" "
					+CARD_NUMBER[p2Deck[i]%13]+" ");
		}
	}
	public  void getP1Card() {
		p1Deck[p1DeckIndex]=deck[deckIndex-1];
		deckIndex--;
		p1DeckIndex++;
	}
	public  void getP2Card() {
		p2Deck[p2DeckIndex]=deck[deckIndex-1];
		deckIndex--;
		p2DeckIndex++;
	}
	public  int p1Score() {
		int returnValue=0;
		for(int i=0; i<p1DeckIndex;i++) {
			int cardScoure=p1Deck[i]%13+1;
			if(cardScoure>10) {
				cardScoure=10;
			}
			returnValue=returnValue+cardScoure;
		}
		for(int i=0;i<p1DeckIndex;i++) {
			if(p1Deck[i]%13==0) {
				if(returnValue+9>21) {
					
				}else {
					returnValue=returnValue+10;
				}
			}
		}
		
		return returnValue;
	}
	public  int p2Score() {
		int returnValue=0;
		for(int i=0; i<p2DeckIndex;i++) {
			int cardScoure=p2Deck[i]%13+1;
			if(cardScoure>10) {
				cardScoure=10;
			}
			returnValue=returnValue+cardScoure;
		}
		for(int i=0;i<p2DeckIndex;i++) {
			if(p2Deck[i]%13==0) {
				if(returnValue+9>21) {
					
				}else {
					returnValue=returnValue+10;
				}
			}
		}
		
		return returnValue;
	}
	public  void init() {
		newGame();
		newCard();
		displayDeckCard();
		mixCard();
	}
	public  void playGame() {
		//플레이어 번갈아 카드 1장씩준다.
		while(isP1GEnd!=true && isP2GEnd!=true ) {
			if(!isP1GEnd) {//게임중인지 확인
				System.out.println("p1님 카드를 계속 받으실? 1.Y 2.N");
				if("1".equals(sc.nextLine())) {
					getP1Card();
					if(p1Score()>21) {
						System.out.println("p1님 카드점수 초과로 종료");
						isP1GEnd=true;
						isP2GEnd=true; 
					}
				}else {
					isP1GEnd=true;
				}
			}
			if(!isP2GEnd) {
				System.out.println("p2님 카드를 계속 받으실? 1.Y 2.N");
				if("1".equals(sc.nextLine())) {
					getP2Card();
					if(p2Score()>21) {
						System.out.println("p2님 카드점수 초과로 종료");
						isP1GEnd=true;
						isP2GEnd=true; 
					}
				}else {
					isP1GEnd=true;
				}
				
			}
			displayP1Deck();
			System.out.println("p1총점:"+p1Score());
			displayP2Deck();
			System.out.println("p2총점:"+p2Score());
		}
	}
	public  void endGame() {
		String result="";
		if(p1Score()>21) {
			result="p2이 승리하였습니다";
		}else if(p2Score()>21) {
			result="p1이 승리하였습니다.";
		}else if (p1Score()>p2Score()) {
			result="p1이 승리하였습니다.";
		}else if (p1Score()<p2Score()) {
			result="p2이 승리하였습니다.";
		}else {
			result="무승부입니다";
		}
		System.out.println("게임결과는 :"+result);
	}
}
public class BlackJack {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlakcJectEx bj = new BlakcJectEx();
		bj.init();
		System.out.println("게임시작");
		bj.playGame();
		bj.endGame();
		System.out.println("게임종료");
	}

}
