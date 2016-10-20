
public class Conta {
    
    private int saldo;

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    public void depositar(int valor){
        this.saldo += valor;
    }
    
    public void sacar(int valor){
        this.saldo -= valor;
    }
}
