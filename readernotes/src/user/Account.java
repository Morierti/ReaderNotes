/**
Copyright (C) 2016  Rodrigo Ramos Rosa

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
**/

package readernotes.src.user;

//import readernotes.src.exceptions.InvalidUserCredentialsException;

public abstract class Account
implements AccountInterface {
	private String _username;
	private String _email;
	private String _password;
	private int _accountID;
	
	public Account(String username, String email, String password, int accountID) {
		this.init(username, email, password, accountID);
	}
	
	private void init(String username, String email, String password, int accountID) {
		this.addUsername(username, password);
		this.addEmail(email, password);
		this.setPassword(password, null);
		this.addAccountID(accountID);
	
	}
	
	public void addUsername(String username, String password) {
		if (credentialsValid(username, password)) {
			_username = username;
		} else {
			//Throw exception;
		}
	}
	
	public String getUsername() {
		return _username;
	}
	
	public void addEmail(String email, String username, String password) {
		if (credentialsValid(username, password)) {
			_email = email;
		} else {
			//Throw exception;
		}
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setPassword(String newPassword, String username, String password) {
		if (credentialsValid(username, password)) {
			_password = password;
		} else {
			//throw exception;
		}
	}
	
	private String getPassword() {
		return _password;
	}
	
	private void addAccountID(int accountID) {
	
	}
	
	public int getAccountID() {
		return _accountID;
	}
	
	public boolean credentialsValid(String username, String password) {
		String userPassword = this.getPassword();
		String currentUsername = this.getUsername();
		boolean validUsername = currentUsername.equals(username)
								|| currentUsername == null;
		boolean validPassword = userPassword.equals(password)
								|| userPassword == null;
		return validUsername && validPassword;
	}
}	
