package br.com.fiap.epictask.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final MessageSource messageSource;

    public TaskService(@Autowired TaskRepository taskRepository, MessageSource messageSource) {
        this.taskRepository = taskRepository;
        this.messageSource = messageSource;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id){
        if(!taskRepository.existsById(id)){
            var message = messageSource.getMessage("task.notFound", null, LocaleContextHolder.getLocale());
            throw new EntityNotFoundException(message);
        }
        taskRepository.deleteById(id);
    }
}
