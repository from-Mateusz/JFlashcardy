package cz.mateusz.flashcardy.web.commands;

import cz.mateusz.flashcardy.web.data.Data;

@FunctionalInterface
public interface ExecutableCommand<I extends Data, O extends Data> {
    O execute(I data) throws CommandExecutionException;
}
