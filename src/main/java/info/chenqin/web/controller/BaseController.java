package info.chenqin.web.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: nathanchen
 * <p>
 * Date: 23/9/16
 * <p>
 * Time: 11:52 AM
 * <p>
 * Description:
 */
public class BaseController
{
    protected ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
}
