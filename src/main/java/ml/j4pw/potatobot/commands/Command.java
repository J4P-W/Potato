package ml.j4pw.potatobot.commands;

import ml.j4pw.potatobot.core.CommandEvent;
import org.javacord.api.entity.permission.PermissionType;

public abstract class Command {
  public String name = "null";
  public String[] alias = null;
  public String help = "No Help";
  public String[] arguments = null;
  public Boolean guildOnly = false;
  public Boolean isOwnerOnly = false;
  public PermissionType[] userPermissions = null;
  public PermissionType[] botPermissions = null;

  public abstract void execute(CommandEvent event);
}
