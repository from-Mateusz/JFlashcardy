package cz.mateusz.flashcardy.web.commands;

import cz.mateusz.flashcardy.web.data.ExistingDeckData;
import cz.mateusz.flashcardy.web.data.UnknownDeckData;

public abstract class CreateDeckCommand implements ExecutableCommand<UnknownDeckData, ExistingDeckData> {
    @Override
    public abstract ExistingDeckData execute(UnknownDeckData data) throws CommandExecutionException;
}
