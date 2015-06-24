package presentation.match.live;

public class MatchTime {
	
	private int minute;
	private int second;
	private int decimal;

	public MatchTime(){
		
	}
	
	public MatchTime(int minute, int second, int decimal){
		try {
			this.initiliseMatchTime(minute, second, decimal);
		} catch (MatchTimeFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initiliseMatchTime(int minute, int second, int decimal) throws MatchTimeFormatException{
		if(second<0 || second>59 || decimal<0 || decimal>99){
			throw new MatchTimeFormatException("比赛时间格式错误");
		}else{
			this.minute = minute;
			this.second = second;
			this.decimal = decimal;
		}
	}
	
	public MatchTime advanceByDecimal() {
		MatchTime time = new MatchTime();
		
		int tempDecimal = decimal - 1;
		if(tempDecimal<0){
			decimal = 99;
			int tempSecond = second - 1;
			if(tempSecond<0){
				second = 59;
				int tempMinute = minute - 1;
				if(tempMinute<0){
					//表示已经结束
					minute = 0;
					second = 0;
					decimal = 0;
//					throw new MatchTimeOverException("Time's up");
				}else{
					minute = tempMinute;
				}
			}else{
				second = tempSecond;
			}
		}else{
			decimal = tempDecimal;
		}
		
		try {
			time.initiliseMatchTime(minute, second, decimal);
		} catch (MatchTimeFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return time;
		
	}
	
	public String toString(){
		
		String str = handleFigure(minute)+":"+handleFigure(second)+"."+handleFigure(decimal);
		return str;
	}
	
	private String handleFigure(int x){
		String result = x+"";
				
		if(x>=0 && x<=9){
			result = "0"+x;
		}
		
		return result;
	}
}
