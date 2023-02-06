package Game;

import java.io.*;

class Balance implements Serializable{
    int balance;

    public int getBalance() {
        return balance;
    }

    public Balance(int balance) {
        this.balance = balance;
    }

    public void SaveBalance(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("Balance.bin")
        ))
        {
            outputStream.writeObject(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
