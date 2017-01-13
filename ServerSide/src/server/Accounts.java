// G00320698 - Rebecca Kane
// Accounts class is just getters and setters with user info, all generated from Source in eclipse

package server;

public class Accounts {

	// Account Variables
		private String name;
		private String address;
		private int accNum;
		private String username;
		private String password;
		private int overdraft; // max 1000
		private int balance;
		
		// Empty Constructor
		public Accounts() {
			super();
		}
		
		// Constructor
		public Accounts(String name, String address, int accNum, String username, String password, int balance) {
			super();
			this.name = name;
			this.address = address;
			this.accNum = accNum;
			this.username = username;
			this.password = password;
			this.balance = balance;
		}
		
		// Gets & Sets
		// Name
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		//Address
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		// Account Number
		public int getAccNum() {
			return accNum;
		}
		public void setAccNum(int accNum) {
			this.accNum = accNum;
		}
		
		// Username
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		// Password
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		// Balance
		public int getBalance() {
			return balance;
		}
		public void setBalance(int balance) {
			this.balance = balance;
		}
		
		// Overdraft
		public float getOverdraft() {
			return overdraft;
		}
		public void setOverdraft(int overdraft) {
			this.overdraft = overdraft;
		}

		@Override
		public String toString() {
			return "Name: " + name + "\nAddress: " + address + "\nAccount Num: " + accNum + "\nUsername: "
					+ username + "\nPassword: " + password + "\n Current Overdraft" + overdraft + "\nBalance: " + balance;
		}
		
		// Lodge to account
		public int makeLodgement(int lodgement){
			balance = balance + lodgement;
			return balance;
		}
		
		// Withdraw from account
		public int makeWithdrawal(int withdrawal){
			balance = balance - withdrawal;
			return balance;
		}
	
}
