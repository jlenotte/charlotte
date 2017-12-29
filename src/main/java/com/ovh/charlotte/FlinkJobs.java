package com.ovh.charlotte;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.operators.MapOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

class FlinkJobs
{

    private final static Logger LOGGER = LoggerFactory.getLogger(FlinkJobs.class);

    /**
     * Return a list of the top N customers
     *
     * // Client + transaction /!\
     *
     * We need to look at all the transactions that "Mr. Bean" did and sum them into one entry
     * Group the transactions of each customer into one entry
     *
     * Sort the list and limit
     * @return r
     */
    DataSet<BestCustomer> getBestCustomerFlink(DataSet<String> data, int amount) throws IOException
    {

        // Convert to object
        MapOperator<Tuple2<String, Double>, BestCustomer> result = data
            .map((MapFunction<String, BestCustomer>) s -> {

                String[] splitter = s.split(",");

                // parse string to double
                double value = Double.parseDouble(splitter[3]);

                // assign values to object params
                BestCustomer customer = new BestCustomer(splitter[0], splitter[1], splitter[2],
                    value);

                return customer;
            })

            // Group by bestcustomers & reduce

            .groupBy(bestCustomer -> bestCustomer.getNichandle())
            .reduce(new ReduceFunction<BestCustomer>()
            {
                @Override
                public BestCustomer reduce(BestCustomer bestCustomer, BestCustomer t1)
                    throws Exception
                {
                    return new BestCustomer(bestCustomer.getNichandle(),
                        bestCustomer.getName(),
                        bestCustomer.getFirstName(),
                        bestCustomer.getTransaction() + t1.getTransaction());
                }
            })

            // Map to Tuple
            .map(new MapFunction<BestCustomer, Tuple2<String, Double>>()
            {
                @Override
                public Tuple2<String, Double> map(BestCustomer bestCustomer) throws Exception
                {
                    String uid = bestCustomer.getNichandle() + bestCustomer.getName() + bestCustomer
                        .getFirstName();
                    Double sum = bestCustomer.getTransaction();
                    Tuple2<String, Double> tuple = new Tuple2<>(uid, sum);
                    return tuple;
                }
            })
            .sortPartition(0, Order.DESCENDING)
            .first(amount)
            .map(new MapFunction<Tuple2<String, Double>, BestCustomer>()
            {
                @Override
                public BestCustomer map(Tuple2<String, Double> stringDoubleTuple2) throws Exception
                {
                    BestCustomer bc = new BestCustomer();
                    return bc;
                }
            });

        return result;
    }

    /**
     * Display the list with flink
     */
    static void displayContent(DataSet<String> text)
    {
        text
            .map((MapFunction<String, Invoice>) s -> {

                String[] splitter = s.split(",");

                // parse string to double
                double value = Double.parseDouble(splitter[3]);

                // parse string to zdate
                ZonedDateTime date = ZonedDateTime.parse(splitter[4]);

                // assign values to object params
                Invoice inv = new Invoice(splitter[0], splitter[1], splitter[2], splitter[3], value, date);

                return inv;
            })
            .map(new MapFunction<Invoice, String>()
            {
                @Override
                public String map(Invoice invoice) throws Exception
                {
                    return invoice.toString();
                }
            }).writeAsText("/tmp/fffuuuuuu"); // once the collect is done, the process stops
    }

    /**
     * Get the total/sum of all transactions per month
     *
     * @return List of doubles
     */
    void getTotalPerMonthFlink(DataSet<String> text) throws IOException
    {
        // read all transactions and sum them for each month

    }


    /**
     * Get top 10 months ever made
     */
    List<Double> getTopTenMonthsFlink(List<Invoice> list) throws IOException
    {
        return null;
    }


    /**
     * Get the total of a customer per year
     *
     * @param list Input a List<> as a parameter
     * @return returns a List<> of Invoices
     * @throws IOException IOException
     */
    List<Invoice> getTotalPerYearPerCustomerFlink(List<Invoice> list) throws IOException
    {
        // Stream & filter the list into a sum
        return null;
    }


    /**
     * Get the total revenue of each year since 2000
     *
     * @param hm HashMap<>
     * @return HashMap<>
     * @throws IOException IOException
     */
    HashMap<String, Double> getTotalPerYearFlink(List<Invoice> list, HashMap<String, Double> hm)
        throws IOException
    {
        return null;
    }
}
