/* Generated By:JavaCC: Do not edit this line. robot.java */
package pruebaslenguaje;
import java.util.HashMap;
import java.util.LinkedList;
public class robot implements robotConstants {

  /* mapa para guardar las variables globales durante la ejecucion del programa */
  private HashMap<String, Integer > userVals = new HashMap< String, Integer>();
/* mapa para guardar las definiciones de las funciones y sus parametros*/
  private HashMap<String,LinkedList<String>> functions = new HashMap<String,LinkedList<String>>();

  public static void main(String args []) throws ParseException
  {
    robot parser = new robot(System.in);
    while (true)
    {
      System.out.println("Reading from standard input...");
      System.out.print("Enter an robot expression:");
      try
      {
        parser.command();
      }
      catch (Exception e)
      {
        System.out.println("NOK.");
        System.out.println(e.getMessage());
        e.printStackTrace();
        break;
      }
      catch (Error e)
      {
        System.out.println("Oops.");
        System.out.println(e.getMessage());
        e.printStackTrace();
        break;
      }
    }
  }

//-----------------------------------------------------------------------------------------------
  final public void command() throws ParseException {
    label_1:
    while (true) {
      if (jj_2_24(2)) {
        if (jj_2_7(2)) {
          jj_consume_token(MOVE);
          numero();
        } else if (jj_2_8(2)) {
          jj_consume_token(RIGHT);
          numero();
        } else if (jj_2_9(2)) {
          jj_consume_token(LEFT);
          numero();
        } else if (jj_2_10(2)) {
          jj_consume_token(ROTATE);
          numero();
        } else if (jj_2_11(2)) {
          jj_consume_token(DROP);
          numero();
        } else if (jj_2_12(2)) {
          jj_consume_token(FREE);
          numero();
        } else if (jj_2_13(2)) {
          jj_consume_token(PICK);
          numero();
        } else if (jj_2_14(2)) {
          jj_consume_token(POP);
          numero();
        } else if (jj_2_15(2)) {
          jj_consume_token(BLOCKEDP);
        } else if (jj_2_16(2)) {
          jj_consume_token(NOP);
        } else if (jj_2_17(2)) {
          jj_consume_token(LOOK);
          if (jj_2_1(2)) {
            jj_consume_token(N);
          } else if (jj_2_2(2)) {
            jj_consume_token(E);
          } else if (jj_2_3(2)) {
            jj_consume_token(W);
          } else if (jj_2_4(2)) {
            jj_consume_token(S);
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        } else if (jj_2_18(2)) {
          jj_consume_token(CHECK);
          if (jj_2_5(2)) {
            jj_consume_token(C);
          } else if (jj_2_6(2)) {
            jj_consume_token(B);
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
          numero();
        } else if (jj_2_19(2)) {
          block();
        } else if (jj_2_20(2)) {
          repeat();
        } else if (jj_2_21(2)) {
          conditional();
        } else if (jj_2_22(2)) {
          define();
        } else if (jj_2_23(2)) {
          function();
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(33);
      } else if (jj_2_25(2)) {
        jj_consume_token(33);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      if (jj_2_26(2)) {
        ;
      } else {
        break label_1;
      }
    }
  }

/**
* reconoce un numero entero sin signo
* @return el valor entero correspondiente al valor reconocido
* VERIFICA SI LA ENTRADA ES UN NUMERO O UNA VARIABLE PREVIAMENTE DEFINIDA POR EL USUARIO.
*/
  final public int numero() throws ParseException, Error {
                Integer total;
    if (jj_2_27(2)) {
      jj_consume_token(NUMERO);
    } else if (jj_2_28(2)) {
      jj_consume_token(VARIABLE);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
                        // si entra al if el numero esta definido por medio de una variable.
                        total = userVals.get(token.image);
                        if(total!=null) {
                          {if (true) return total;}
                        }
                        //si no entonces debe ser un numero explicito.
                        else {
                                try
                                {
                                        total = Integer.parseInt(token.image);
                                        {if (true) return total;}
                                }
                                catch (Exception e)
                                {
                                  {if (true) throw new Error("Error al intentar convertir: "+token.image +" en un numero.");}
                                }
                        }
    throw new Error("Missing return statement in function");
  }

  final public void block() throws ParseException {
    jj_consume_token(34);
    jj_consume_token(BLOCK);
    command();
    jj_consume_token(35);
  }

  final public void repeat() throws ParseException {
    jj_consume_token(REPEAT);
    numero();
    jj_consume_token(36);
    command();
    jj_consume_token(37);
  }

  final public void conditional() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(BLOCKEDP);
    jj_consume_token(36);
    command();
    jj_consume_token(37);
  }

  final public void define() throws ParseException {
  String name="";
  Integer value;
    jj_consume_token(DEFINE);
    jj_consume_token(VARIABLE);
    name = token.image;
    jj_consume_token(NUMERO);
    try {
      value = Integer.parseInt(token.image);
    }
    catch(Exception e) {
      {if (true) throw new ParseException("Una variable solo puede guardar numeros");}
    }
    userVals.put(name, value);
  }

  final public void function() throws ParseException {
  String name = "";
  LinkedList<String> params = new LinkedList<String>();
    jj_consume_token(TO);
    jj_consume_token(VARIABLE);
    name = token.image;
    label_2:
    while (true) {
      if (jj_2_29(2)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(38);
      jj_consume_token(VARIABLE);
     params.add(":"+token.image);
    }
    //retorna el viejo valor (si ya habia uno con esa misma key) y null si es la primera vez que se inserta.
    if(functions.put(name,params)!=null) {
      {if (true) throw new ParseException("La funcion: "+ name +" ya fue previamente definida.");}
    }
    jj_consume_token(OUTPUT);
    command();
    jj_consume_token(END);
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  private boolean jj_2_17(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(16, xla); }
  }

  private boolean jj_2_18(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(17, xla); }
  }

  private boolean jj_2_19(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(18, xla); }
  }

  private boolean jj_2_20(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_20(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(19, xla); }
  }

  private boolean jj_2_21(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_21(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(20, xla); }
  }

  private boolean jj_2_22(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_22(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(21, xla); }
  }

  private boolean jj_2_23(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_23(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(22, xla); }
  }

  private boolean jj_2_24(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_24(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(23, xla); }
  }

  private boolean jj_2_25(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_25(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(24, xla); }
  }

  private boolean jj_2_26(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_26(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(25, xla); }
  }

  private boolean jj_2_27(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_27(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(26, xla); }
  }

  private boolean jj_2_28(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_28(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(27, xla); }
  }

  private boolean jj_2_29(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_29(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(28, xla); }
  }

  private boolean jj_3_11() {
    if (jj_scan_token(DROP)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_15() {
    if (jj_scan_token(BLOCKEDP)) return true;
    return false;
  }

  private boolean jj_3_10() {
    if (jj_scan_token(ROTATE)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_14() {
    if (jj_scan_token(POP)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(LEFT)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(MOVE)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_scan_token(TO)) return true;
    if (jj_scan_token(VARIABLE)) return true;
    return false;
  }

  private boolean jj_3R_5() {
    if (jj_scan_token(REPEAT)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_26() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_24()) {
    jj_scanpos = xsp;
    if (jj_3_25()) return true;
    }
    return false;
  }

  private boolean jj_3_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3_9()) {
    jj_scanpos = xsp;
    if (jj_3_10()) {
    jj_scanpos = xsp;
    if (jj_3_11()) {
    jj_scanpos = xsp;
    if (jj_3_12()) {
    jj_scanpos = xsp;
    if (jj_3_13()) {
    jj_scanpos = xsp;
    if (jj_3_14()) {
    jj_scanpos = xsp;
    if (jj_3_15()) {
    jj_scanpos = xsp;
    if (jj_3_16()) {
    jj_scanpos = xsp;
    if (jj_3_17()) {
    jj_scanpos = xsp;
    if (jj_3_18()) {
    jj_scanpos = xsp;
    if (jj_3_19()) {
    jj_scanpos = xsp;
    if (jj_3_20()) {
    jj_scanpos = xsp;
    if (jj_3_21()) {
    jj_scanpos = xsp;
    if (jj_3_22()) {
    jj_scanpos = xsp;
    if (jj_3_23()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    if (jj_scan_token(33)) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(RIGHT)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3R_4() {
    if (jj_scan_token(34)) return true;
    if (jj_scan_token(BLOCK)) return true;
    return false;
  }

  private boolean jj_3_28() {
    if (jj_scan_token(VARIABLE)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(S)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(W)) return true;
    return false;
  }

  private boolean jj_3_27() {
    if (jj_scan_token(NUMERO)) return true;
    return false;
  }

  private boolean jj_3R_7() {
    if (jj_scan_token(DEFINE)) return true;
    if (jj_scan_token(VARIABLE)) return true;
    return false;
  }

  private boolean jj_3R_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_27()) {
    jj_scanpos = xsp;
    if (jj_3_28()) return true;
    }
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(B)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(E)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(C)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(N)) return true;
    return false;
  }

  private boolean jj_3_23() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3_25() {
    if (jj_scan_token(33)) return true;
    return false;
  }

  private boolean jj_3_22() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3_21() {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_20() {
    if (jj_3R_5()) return true;
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_scan_token(IF)) return true;
    if (jj_scan_token(BLOCKEDP)) return true;
    return false;
  }

  private boolean jj_3_19() {
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_29() {
    if (jj_scan_token(38)) return true;
    if (jj_scan_token(VARIABLE)) return true;
    return false;
  }

  private boolean jj_3_18() {
    if (jj_scan_token(CHECK)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3_6()) return true;
    }
    return false;
  }

  private boolean jj_3_13() {
    if (jj_scan_token(PICK)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_17() {
    if (jj_scan_token(LOOK)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_1()) {
    jj_scanpos = xsp;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3_4()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3_12() {
    if (jj_scan_token(FREE)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_16() {
    if (jj_scan_token(NOP)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public robotTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[29];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public robot(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public robot(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new robotTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public robot(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new robotTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public robot(robotTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(robotTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[39];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 0; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 39; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 29; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
            case 15: jj_3_16(); break;
            case 16: jj_3_17(); break;
            case 17: jj_3_18(); break;
            case 18: jj_3_19(); break;
            case 19: jj_3_20(); break;
            case 20: jj_3_21(); break;
            case 21: jj_3_22(); break;
            case 22: jj_3_23(); break;
            case 23: jj_3_24(); break;
            case 24: jj_3_25(); break;
            case 25: jj_3_26(); break;
            case 26: jj_3_27(); break;
            case 27: jj_3_28(); break;
            case 28: jj_3_29(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
