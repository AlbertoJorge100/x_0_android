package com.example.pn17_i04_001_juego_x_0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Boolean ValidarLetra;
    private Boolean letraX;
    private Button btn00;
    private Button btn01;
    private Button btn02;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn20;
    private Button btn21;
    private Button btn22;
    private TextView lblResultado;
    private TextView txbLetra;
    private String[][]matriz=new String[3][3];
    private String Letra1;
    private String Letra2;
    private int Cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ValidarLetra=false;
        btn00=findViewById(R.id.btn00);
        btn01=findViewById(R.id.btn01);
        btn02=findViewById(R.id.btn02);
        btn10=findViewById(R.id.btn10);
        btn11=findViewById(R.id.btn11);
        btn12=findViewById(R.id.btn12);
        btn20=findViewById(R.id.btn20);
        btn21=findViewById(R.id.btn21);
        btn22=findViewById(R.id.btn22);
        Button btnReiniciar=findViewById(R.id.btnReiniciar);
        lblResultado=findViewById(R.id.lblResultado);
        txbLetra=findViewById(R.id.txbLetra);
        btn00.setOnClickListener(this);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        Reiniciar();
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn00.setText("");
                btn01.setText("");
                btn02.setText("");
                btn10.setText("");
                btn11.setText("");
                btn12.setText("");
                btn20.setText("");
                btn21.setText("");
                btn22.setText("");
                lblResultado.setText("No hay ganador !");
                MainActivity.this.Cont=0;
                Reiniciar();
                ValidarLetra=false;
                txbLetra.setText("");
            }
        });
    }

    private void Reiniciar(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.matriz[i][j]="";
            }
        }
    }
    private String ValidarLetra(){
        String letra=txbLetra.getText().toString().trim();
        if(!letra.equals("") && (letra.equals("x") || letra.equals("0"))){
            return letra;
        }else{
            txbLetra.setError("Ingrese una letra valida");
            return "";
        }
    }

    private String Clave(){
        if(++Cont%2 == 0){
            return Letra2;
        }else{
            return Letra1;
        }
    }

    private void Colorear(Button btn, int x, int y){
        String letra="";
        if(Cont==0){
            letra=ValidarLetra();
            if(!letra.equals("")){
                ValidarLetra=true;
                if(letra.equals("x")){
                    Letra1="x";
                    Letra2="0";
                }else{
                    Letra1="0";
                    Letra2="x";
                }
            }
        }
        if(ValidarLetra){
            letra=Clave();
            matriz[x][y]=letra;
            btn.setText(letra);
            if(ValidarGanador(letra)){
                lblResultado.setText("El ganador es: "+letra);
            }
        }
    }

    private Boolean ValidarGanador(String letra){
        int conti0=0, conti1=0, conti2=0, contj0=0, contj1=0, contj2=0,cont=0
                ,contx1=0, contx2=0;
        for(int i=0;i<3;i++){
            cont++;
            for(int j=0;j<3;j++){
                if(matriz[i][j].equals(letra) && i==0 ){
                    conti0++;
                    if(conti0==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && i==1){
                    conti1++;
                    if(conti1==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && (i==2) ){
                    conti2++;
                    if(conti2==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && (j==0)){
                    contj0++;
                    if(contj0==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && j==1){
                    contj1++;
                    if(contj1==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && (j==2) ){
                    contj2++;
                    if(contj2==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && ((i==cont-1 && j==cont-1))){
                    contx1++;
                    if(contx1==3)
                        return true;
                }
                if(matriz[i][j].equals(letra) && ((i==2 && j==0) || (i==1 && j==1) || (i==0 && j==2))){
                    contx2++;
                    if(contx2==3)
                        return true;
                }
            }
        }
        return false;
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn00:
                Colorear(this.btn00,0,0);
                break;
            case R.id.btn01:
                Colorear(this.btn01,0,1);
                break;
            case R.id.btn02:
                Colorear(this.btn02,0,2);
                break;
            case R.id.btn10:
                Colorear(this.btn10,1,0);
                break;
            case R.id.btn11:
                Colorear(this.btn11,1,1);
                break;
            case R.id.btn12:
                Colorear(this.btn12,1,2);
                break;
            case R.id.btn20:
                Colorear(this.btn20,2,0);
                break;
            case R.id.btn21:
                Colorear(this.btn21,2,1);
                break;
            case R.id.btn22:
                Colorear(this.btn22,2,2);
                break;
        }
    }
}