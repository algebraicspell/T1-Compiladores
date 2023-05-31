package compiladores.t1.LA;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class CompiladoresT1 {

    public static void main(String[] args) {
        String arquivoSaida = args[1];
        
        try {
            PrintWriter pw = new PrintWriter(arquivoSaida);
            try {
                CharStream cs = CharStreams.fromFileName(args[0]);
                LA linguagemAlg = new LA(cs);
                Token tolkien = null;
                while ((tolkien = linguagemAlg.nextToken()).getType() != Token.EOF) {
                    if ("Erro".equals(LA.VOCABULARY.getDisplayName(tolkien.getType()))) {
                        pw.println("Linha " + tolkien.getLine() + ": " + tolkien.getText() + " - simbolo nao identificado");
                        break;
                    } else if ("ComentarioNaoFechado".equals(LA.VOCABULARY.getDisplayName(tolkien.getType()))) {
                        pw.println("Linha " + tolkien.getLine() + ": comentario nao fechado");
                        break;
                    } else if ("CadeiaLiteralNaoFechada".equals(LA.VOCABULARY.getDisplayName(tolkien.getType()))) {
                        pw.println("Linha " + tolkien.getLine() + ": cadeia literal nao fechada");
                        break;
                    } else if ("FechamentoComentarioIsolado".equals(LA.VOCABULARY.getDisplayName(tolkien.getType()))) {
                        pw.println("Linha " + tolkien.getLine() + ": " + tolkien.getText() + " - simbolo nao identificado");
                        break;
                    } else {
                        pw.println("<\'" + tolkien.getText() + "\'," + LA.VOCABULARY.getDisplayName(tolkien.getType()) + ">");
                    }
                }
                pw.close();
            } catch (IOException ex) {
                System.out.print(ex + "\n");
            }
        } catch(FileNotFoundException fnfe) {
        }
    }
}