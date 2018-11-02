package ml.j4pw.potatobot.core.constants;

import java.util.Arrays;
import java.util.List;

public class RuntimeConstants {
  private String PREFIX = "";
  private List<Long> OWNERS = Arrays.asList(358794817595113476L, 245470898293833729L);
  private String TOKEN = "";

  public String getPREFIX() {
    return PREFIX;
  }

  public void setPREFIX(String PREFIX) {
    this.PREFIX = PREFIX;
  }

  public List<Long> getOWNERS() {
    return OWNERS;
  }

  public void setOWNERS(List<Long> OWNERS) {
    this.OWNERS = OWNERS;
  }

  public String getTOKEN() {
    return TOKEN;
  }

  public void setTOKEN(String TOKEN) {
    this.TOKEN = TOKEN;
  }
}
