package example.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import example.spring.rest.LoginServiceController;


public class EmailScheduler implements Job  {
	
	private static Logger LOG = LogManager
			.getLogger(LoginServiceController.class);
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LOG.info("Trigger Email Scheduler");
	}
}
