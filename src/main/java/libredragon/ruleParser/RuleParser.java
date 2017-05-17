/* RuleParser.java */
/* Generated By:JavaCC: Do not edit this line. RuleParser.java */
package libredragon.ruleParser;
import java.util.ArrayList;
import libredragon.model.*;
import java.io.InputStream;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
public class RuleParser implements RuleParserConstants {
private static RulesConfiguration rules;
        public static void main(String args[]) throws ParseException {
                RuleParser parser = new RuleParser(System.in);
                Configuration.rules = new RulesConfiguration();
                parser.Rule();
        }
public static void readRules(InputStream stream, RulesConfiguration r) throws ParseException {
        rules = r;
        if (jj_initialized_once){
        RuleParser.ReInit(stream);
        RuleParser.RuleList();
        }
        else{
        RuleParser parser = new RuleParser(stream);
        parser.RuleList();
        }
        }
        public static void writeRule(Rule rule) {
        final String chemin = "config//customRules.cfg";
        final File fichier =new File(chemin);
        try {
        if(!fichier.exists())
        fichier.createNewFile();
        final FileWriter writer = new FileWriter(fichier, true);
        try {
        writer.write(rule.getInputModel().getExpr()+"\u0009"+"=(\u00a7Custom)=>"+rule.getResultModel().getExpr()+"\u005cn");
        } finally {
        writer.close();
        }
        } catch (Exception e) {
        System.out.println("Impossible de creer le fichier");
        }
        }
        /**
	* Lis les expressions jouables ainsi que les expression a atteindre pour finir la
	* session de jeu dans le fichier config/formulas.cfg
	*/
        public static void readExpressionsJouables(ArrayList<Pair<Expression,Expression>> liste ){
        String configPath = "config";
        BufferedReader lecteurAvecBuffer = null;
        String ligne,expression,resultat;
        int i;
        try {
        lecteurAvecBuffer = new BufferedReader(new FileReader(configPath + "/formula.cfg"));
        }
        catch(FileNotFoundException exc){
        System.out.println("Erreur d'ouverture");
        }
        try {
        while ((ligne = lecteurAvecBuffer.readLine()) != null){
        i = 0;
        while (i+2 < ligne.length() && ligne.substring(i,i+2).compareTo("=>") != 0){
        i++;
        }
        expression = ligne.substring(0, i-1);
        resultat = ligne.substring(i+2);
        try {
        InputStream expInitial = new ByteArrayInputStream(expression.getBytes(StandardCharsets.UTF_8));
        InputStream expFinal = new ByteArrayInputStream(resultat.getBytes(StandardCharsets.UTF_8));
        liste.add(new Pair (RuleParser.readExpression(expInitial),RuleParser.readExpression(expFinal)));
        } catch (libredragon.ruleParser.ParseException e) {
        e.printStackTrace();
        }
        }
        }
        catch (IOException e){
        e.printStackTrace();
        }
        }
        public static Expression readExpression(InputStream stream) throws ParseException {
                Expression expression = null;
        if (jj_initialized_once){
                RuleParser.ReInit(stream);
        expression = Terme0();
        }
        else {
        RuleParser parser =  new RuleParser(stream);
        expression = parser.Terme0();
        }
                return expression;
        }

  static final public Expression Epsilon() throws ParseException {
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public void RuleList() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MOINS_B_OP:
      case SQRT_LEFT:
      case NOT_LEFT:
      case CONGRAT_LEFT:
      case ROOT_LEFT:
      case PARENTHESIS_LEFT:
      case BRACKETS_LEFT:
      case LITTERAL:
      case NOMBRE:
      case EXPRESSION:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      Rule();
    }
    jj_consume_token(0);
  }

  static final public void Rule() throws ParseException {Expression exp1;
    exp1 = Terme0();
    RuleBis(exp1);
  }

  static final public void RuleBis(Expression exp1) throws ParseException {Expression exp2;
        Token input_type_rule;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LEFT_RULE_EQUIVALENT:{
      jj_consume_token(LEFT_RULE_EQUIVALENT);
      input_type_rule = jj_consume_token(RULE_INPUT_TYPE);
      jj_consume_token(RIGHT_RULE);
      exp2 = Terme0();
rules.addRule(input_type_rule.image.substring(1), new Rule(exp1, exp2));
                rules.addRule(input_type_rule.image.substring(1), new Rule(exp2, exp1));
                System.out.println(rules.getRules());
      break;
      }
    case LEFT_RULE_NOT_EQUIVALENT:{
      jj_consume_token(LEFT_RULE_NOT_EQUIVALENT);
      input_type_rule = jj_consume_token(RULE_INPUT_TYPE);
      jj_consume_token(RIGHT_RULE);
      exp2 = Terme0();
rules.addRule(input_type_rule.image.substring(1), new Rule(exp1, exp2));
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Axiome() throws ParseException {Expression exp;
    exp = Terme0();
    jj_consume_token(0);
System.out.println(exp.expressionToString());
  }

  static final public Expression Terme0() throws ParseException {Expression exp;
                Expression exp2;
    exp = Terme1();
    exp2 = Terme0Bis(exp);
{if ("" != null) return exp2 == null ? exp : exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme0Bis(Expression exp) throws ParseException {Token symbol;
        Expression exp1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OR_OP:{
      symbol = jj_consume_token(OR_OP);
      exp1 = Terme0();
{if ("" != null) return new BinaryExpression("OR", exp, exp1);}
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      exp1 = Epsilon();
{if ("" != null) return exp1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme1() throws ParseException {Expression exp;
                Expression exp2;
    exp = Terme2();
    exp2 = Terme1Bis(exp);
{if ("" != null) return exp2 == null ? exp : exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme1Bis(Expression exp) throws ParseException {Token symbol;
        Expression exp1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND_OP:{
      symbol = jj_consume_token(AND_OP);
      exp1 = Terme1();
{if ("" != null) return new BinaryExpression("AND", exp, exp1);}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      exp1 = Epsilon();
{if ("" != null) return exp1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme2() throws ParseException {Expression exp;
                Expression exp2;
    exp = Terme3();
    exp2 = Terme2Bis(exp);
{if ("" != null) return exp2 == null ? exp : exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme2Bis(Expression exp) throws ParseException {Token symbol;
        Expression exp1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case SUPEGAL_OP:{
      symbol = jj_consume_token(SUPEGAL_OP);
      exp1 = Terme2();
{if ("" != null) return new BinaryExpression("SUPEGAL", exp, exp1);}
      break;
      }
    case INFEGAL_OP:{
      symbol = jj_consume_token(INFEGAL_OP);
      exp1 = Terme2();
{if ("" != null) return new BinaryExpression("INFEGAL", exp, exp1);}
      break;
      }
    case SUP_OP:{
      symbol = jj_consume_token(SUP_OP);
      exp1 = Terme2();
{if ("" != null) return new BinaryExpression("SUP", exp, exp1);}
      break;
      }
    case INF_OP:{
      symbol = jj_consume_token(INF_OP);
      exp1 = Terme2();
{if ("" != null) return new BinaryExpression("INF", exp, exp1);}
      break;
      }
    case DIFF_OP:{
      symbol = jj_consume_token(DIFF_OP);
      exp1 = Terme2();
{if ("" != null) return new BinaryExpression("DIFF", exp, exp1);}
      break;
      }
    case EGAL_OP:{
      symbol = jj_consume_token(EGAL_OP);
      exp1 = Terme2();
{if ("" != null) return new BinaryExpression("EGAL", exp, exp1);}
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      exp1 = Epsilon();
{if ("" != null) return exp1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme3() throws ParseException {Expression exp;
                Expression exp2;
    exp = Terme4();
    exp2 = Terme3Bis(exp);
{if ("" != null) return exp2 == null ? exp : exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme3Bis(Expression exp) throws ParseException {Token symbol;
        Expression exp1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MOINS_B_OP:{
      symbol = jj_consume_token(MOINS_B_OP);
      exp1 = Terme3();
{if ("" != null) return new BinaryExpression("MOINS_B", exp, exp1);}
      break;
      }
    case PLUS_OP:{
      symbol = jj_consume_token(PLUS_OP);
      exp1 = Terme3();
{if ("" != null) return new BinaryExpression("PLUS", exp, exp1);}
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      exp1 = Epsilon();
{if ("" != null) return exp1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme4() throws ParseException {Expression exp;
                Expression exp2;
    exp = Terme5();
    exp2 = Terme4Bis(exp);
{if ("" != null) return exp2 == null ? exp : exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme4Bis(Expression exp) throws ParseException {Token symbol;
        Expression exp1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DIVIDE_OP:{
      symbol = jj_consume_token(DIVIDE_OP);
      exp1 = Terme4();
{if ("" != null) return new BinaryExpression("DIVIDE", exp, exp1);}
      break;
      }
    case FOIS_OP:{
      symbol = jj_consume_token(FOIS_OP);
      exp1 = Terme4();
{if ("" != null) return new BinaryExpression("FOIS", exp, exp1);}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      exp1 = Epsilon();
{if ("" != null) return exp1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme5() throws ParseException {Expression exp;
                Expression exp2;
    exp = Terme6();
    exp2 = Terme5Bis(exp);
{if ("" != null) return exp2 == null ? exp : exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme5Bis(Expression exp) throws ParseException {Token symbol;
        Expression exp1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case POWER_OP:{
      symbol = jj_consume_token(POWER_OP);
      exp1 = Terme5();
{if ("" != null) return new BinaryExpression("POWER", exp, exp1);}
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      exp1 = Epsilon();
{if ("" != null) return exp1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Terme6() throws ParseException {Token symbol;
        Expression exp;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case SQRT_LEFT:{
      symbol = jj_consume_token(SQRT_LEFT);
      exp = Terme6();
{if ("" != null) return new UnaryExpression("SQRT", exp);}
      break;
      }
    case MOINS_B_OP:{
      symbol = jj_consume_token(MOINS_B_OP);
      exp = Terme6();
{if ("" != null) return new UnaryExpression("MOINS_U", exp);}
      break;
      }
    case NOT_LEFT:{
      symbol = jj_consume_token(NOT_LEFT);
      exp = Terme6();
{if ("" != null) return new UnaryExpression("NOT", exp);}
      break;
      }
    case CONGRAT_LEFT:{
      symbol = jj_consume_token(CONGRAT_LEFT);
      exp = Terme6();
{if ("" != null) return new UnaryExpression("CONGRAT", exp);}
      break;
      }
    case ROOT_LEFT:{
      symbol = jj_consume_token(ROOT_LEFT);
      exp = Terme6();
{if ("" != null) return new UnaryExpression("ROOT", exp);}
      break;
      }
    case PARENTHESIS_LEFT:
    case BRACKETS_LEFT:
    case LITTERAL:
    case NOMBRE:
    case EXPRESSION:{
      exp = UnaireDroit();
{if ("" != null) return exp;}
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression UnaireDroit() throws ParseException {Expression primaire;
        Expression exp;
    primaire = Primaire();
    exp = UnaireDroitBis(primaire);
if(exp == null) {if ("" != null) return primaire;} {if ("" != null) return exp;}
    throw new Error("Missing return statement in function");
  }

  static final public Expression UnaireDroitBis(Expression exp) throws ParseException {Token symbol;
        Expression exp2;
        Expression exp3;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NOT_LEFT:{
      symbol = jj_consume_token(NOT_LEFT);
exp2 = new UnaryExpression("FACTORIAL",exp);
      exp3 = UnaireDroitBis(exp2);
{if ("" != null) return exp3 == null ? exp2 : exp3;}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      exp2 = Epsilon();
{if ("" != null) return exp2;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expression Primaire() throws ParseException {Token symbolLeft;
        Token symbolRight;
        Expression expression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PARENTHESIS_LEFT:{
      symbolLeft = jj_consume_token(PARENTHESIS_LEFT);
      expression = Terme0();
      symbolRight = jj_consume_token(PARENTHESIS_RIGHT);
{if ("" != null) return new UnaryExpression("PARENTHESIS",expression);}
      break;
      }
    case BRACKETS_LEFT:{
      symbolLeft = jj_consume_token(BRACKETS_LEFT);
      expression = Terme0();
      symbolRight = jj_consume_token(BRACKETS_RIGHT);
{if ("" != null) return expression;}
      break;
      }
    case LITTERAL:{
      symbolLeft = jj_consume_token(LITTERAL);
{if ("" != null) return new PrimaryExpression("LITTERAL",symbolLeft.image);}
      break;
      }
    case NOMBRE:{
      symbolLeft = jj_consume_token(NOMBRE);
{if ("" != null) return new PrimaryExpression("NOMBRE",symbolLeft.image);}
      break;
      }
    case EXPRESSION:{
      symbolLeft = jj_consume_token(EXPRESSION);
{if ("" != null) return new PrimaryExpression("EXPRESSION",symbolLeft.image);}
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public RuleParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1d7c2000,0xc0000000,0x20,0x40,0x1f80,0x6000,0x18000,0x20000,0x1d7c2000,0x80000,0x1d400000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public RuleParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public RuleParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new RuleParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public RuleParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new RuleParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public RuleParser(RuleParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(RuleParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[33];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 11; i++) {
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
    for (int i = 0; i < 33; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
