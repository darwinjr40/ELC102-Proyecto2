package my.game.Models.estructura;

import java.util.Arrays;

public class MatrizSparce {
    private byte VD[];//vector de datos
    private int VFC[];// vector fila columna
    private byte ep;// elemento predominante
    private int Nfil;//num. de filas
    private int Ncol;//num. de columnas
    private int dim;// dimencion del vector

    public MatrizSparce(int Nfil, int Ncol,byte ep) {
        this.VD = new byte[5];
        this.VFC = new int [5];
        this.ep = ep;
        this.Nfil = Nfil;
        this.Ncol = Ncol;
        this.dim = -1;
    }

    public MatrizSparce(int Nfil, int Ncol) {
        this(Nfil, Ncol, (byte)0);
    }

    public void set(int fila,int col, byte ele){
        if((fila>Nfil)||(col>Ncol)){
            System.out.println("Error fila y columna fuera de Rango");
            System.exit(1);
        }
        else{
            int FC=((fila-1)*Ncol)+col;
            int posFC=buscarFC(fila,col);
            if (posFC>dim)//no existe
            { if (ele!=ep){//inserta
                Redimensionar();//verifica si hay espacio para ma elementos
                dim++;
                VD[dim]=ele;
                VFC[dim]=FC;
            }
            }
            else{//existe el elemento en la fila y columna, toca remplazar
                if (ele!=ep){//es diferente al elementoPredominante
                    VD[posFC]=ele;
                }
                else{ //eliminar el elemento ya que se inserto un (elemento=elementoPredominante)
                    for (int i = posFC; i < dim; i++) {
                        VD[i]=VD[i+1];
                        VFC[i]=VFC[i+1];
                    }
                    dim--;
                }

            }
        }
    }

    public byte get(int fila, int col){
        int i=buscarFC(fila,col);
        if (i<=dim)
            return(VD[i]);
        else
            return(ep);
    }

    public byte getDOWN(int f, int c){
        return this.get((f+1) % this.Nfil, c);
    }

    public byte getUP(int f, int c){
        return this.get((f-1+Nfil) % Nfil, c);
    }

    public byte getLEFT(int f, int c){
        return this.get( f,(c-1+Ncol) % Ncol);
    }

    public byte getRIGHT(int f, int c){
        return this.get( f,(c+1) % Ncol);
    }

    public byte getEleSig(int f, int c, Direction d){
        byte ele = 0;
        switch (d){
            case UP :  ele = getUP(f, c); break;
            case DOWN :  ele = getDOWN(f, c); break;
            case LEFT :  ele = getLEFT(f, c); break;
            case RIGHT : ele = getRIGHT(f, c); break;
        }
        return ele;
    }

    public void intercambiarPos(int f, int c, int f1, int c1){
        byte aux = get(f, c);
        set(f, c, get(f1,c1));
        set(f1, c1, aux);
    }

    public void clear(){
        Arrays.fill(VD, (byte) 0);
        Arrays.fill(VFC, (byte) 0);
    }
    public byte getEp() {
        return ep;
    }

    public void setEp(byte ep) {
        this.ep = ep;
    }

    public int getNfil() {
        return Nfil;
    }

    public void setNfil(int nfil) {
        Nfil = nfil;
    }

    public int getNcol() {
        return Ncol;
    }

    public void setNcol(int ncol) {
        Ncol = ncol;
    }

    private void Redimensionar() {
        if (dim+1 == VFC.length) {
            VFC = Arrays.copyOf(VFC, dim + 6);
            VD = Arrays.copyOf(VD, dim + 6);
        }
    }

    private int buscarFC(int fila,int col){
        int i=0;
        int FC=((fila-1)*Ncol)+col;
        while ((i<=dim)&&(VFC[i]!=FC))
            i++;
        return(i);
    }


    @Override
    public String toString() {

        String S="M=["+'\n';

        for (int i = 0; i < Nfil; i++) {
            for (int j = 0; j<Ncol; j++) {
                S=S+ get(i,j)+"   ";
            }
            S=S+'\n';
        }
        S=S+"]";

        return (S);
    }


}
