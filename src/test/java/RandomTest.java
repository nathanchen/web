/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:53 PM
 * <p>
 * Description:
 */
public class RandomTest
{
    @org.junit.Test
    public void testFinal()
    {
        final Sample sample = new Sample();
        sample.setAge(123);
        System.out.println(sample.getAge());
    }

    private void changeStr(String str)
    {
        str = "345";
    }

    private void changeInt(int num)
    {
        num = 345;
    }

    class Sample {
        private String name;
        private int age;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getAge()
        {
            return age;
        }

        public void setAge(int age)
        {
            this.age = age;
        }
    }
}
