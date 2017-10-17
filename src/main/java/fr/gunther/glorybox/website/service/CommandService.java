package fr.gunther.glorybox.website.service;

import fr.gunther.glorybox.website.dto.CommandDTO;
import fr.gunther.glorybox.website.dto.FormCommandDTO;

import fr.gunther.glorybox.website.entity.Command.Status;
import java.util.List;

public interface CommandService {

    boolean saveCommand(FormCommandDTO form);

    List<CommandDTO> findAllPendingCommand();

    List<CommandDTO> findAllOldCommand();

    List<CommandDTO> findAllValidateCommand();

        void updateStatus(Status status, Long id);
}
