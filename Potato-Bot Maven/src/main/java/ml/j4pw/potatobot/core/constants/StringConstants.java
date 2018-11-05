package ml.j4pw.potatobot.core.constants;

public enum StringConstants {
  GUILD_ONLY("This command cannot be used in DMs."),
  OWNER_ONLY("This command can only be used by bot Owners."),
  USER_INSUFFICIENT_PERMS("You do not have enough Permissions to use this command"),
  BOT_INSUFFICIENT_PERMS("I do not have enough Permissions");

  private String statement;

  StringConstants(String statement) {
    this.statement = statement;
  }

  public String getStatement() {
    return statement;
  }
}
