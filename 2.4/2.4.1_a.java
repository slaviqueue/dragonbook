import java.io.*;

class Main {
  public static void main(String[] args) throws IOException, Exception {
    Reader reader = new Reader();

    System.out.println("Enter a +aa-like expression:\n");

    String code = reader.read();
    Analyzer analyzer = new Analyzer(code);

    analyzer.s();
  }
}

class Analyzer {
  String code; 
  int cursor = 0;

  int ident = 0;

  Analyzer(String code) {
    this.code = code;
  }

  void next() {
    cursor++;
  }

  char lookahead() {
    return code.charAt(cursor);
  }

  void print(char c) {
    System.out.println("  ".repeat(ident) + c);
  }

  public void s() throws Exception {
    char lookahead = lookahead();

    ident += 1;
    print(lookahead);

    switch (lookahead) {
      case '+':
        next();
        s();
        s();
        break;

      case '-':
        next();
        s();
        s();
        break;
      
      case 'a':
        next();
        break;

      default:
        throw new Exception("unknown character \"" + lookahead() + "\"");
    }

    ident -= 1;
  }
}

class Reader {
  String read() throws IOException {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    String s = buffer.readLine();
    return s;
  }
}
