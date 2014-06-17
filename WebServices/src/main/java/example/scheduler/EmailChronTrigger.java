package example.scheduler;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import example.spring.rest.LoginServiceController;


public class EmailChronTrigger {
	
	private static Logger LOG = LogManager
			.getLogger(LoginServiceController.class);

	
    // cron expression example (every 20 seconds): "0/20 * * * * ?"
	public void init(String cronExpression) {
		
		JobDetail emailJob = JobBuilder.newJob(EmailScheduler.class)
		    .withIdentity("emailScheduler", "email")
		    .build();

		CronTrigger trigger = TriggerBuilder.newTrigger()
		    .withIdentity("emailTrigger", "email")
		    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
		    .build();
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched;
		
		try {
			
			sched = sf.getScheduler();
			sched.scheduleJob(emailJob, trigger);
			sched.start();
			
		} catch (SchedulerException e) {
			LOG.error("Error starting up email scheduler",e);
		}
	}
}
