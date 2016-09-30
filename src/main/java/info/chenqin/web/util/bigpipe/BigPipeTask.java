package info.chenqin.web.util.bigpipe;

import lombok.Data;

import java.util.*;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:20 AM
 * <p>
 * Description:
 */
@Data
public class BigPipeTask
{
    private final Map<String, Object> model;

    private String framePageletName;
    private List<String> pageletNames;

    public BigPipeTask(final Map<String, Object> model, final String framePageletName, final String... pageletNames)
    {
        this.model = model;
        this.framePageletName = framePageletName;
        if (pageletNames != null && pageletNames.length > 0)
        {
            this.pageletNames = Arrays.asList(pageletNames);
        }
        else
        {
            this.pageletNames = new ArrayList<>();
        }
    }
}
