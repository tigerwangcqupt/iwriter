

import com.alibaba.dubbo.rpc.RpcContext;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.qstone.entity.transaction.dto.TransactionFlowRecord;
import com.yryz.qstone.modules.transaction.api.OpenTransactionApi;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.profit.ProfitApi;
import com.yryz.writer.modules.writer.entity.Writer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class BankAPITest {


   /* @Autowired
    IdAPI idAPI;*/

   @Autowired
    OpenTransactionApi openTransactionApi;

    @Autowired
    ProfitApi profitApi;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

/*    //绑定资金主体
    @Test
    public void bindCapital(){
        Writer writer = new Writer();
        writer.setUserName("tiger_test003");
        RpcResponse<Writer> rpcResponse =  profitApi.bindCapital(writer);
        System.out.println(rpcResponse);
    }*/

/*    //绑定流水
    public void bindFlow(Draft draft){
        //稿费
        BigDecimal draftFee = draft.getDraftFee();

        Writer writer = new Writer();
        writer.setUserName("tiger_test001");
        RpcResponse<Owner> rpcResponse =  profitApi.bindCapital(writer);
        System.out.println(rpcResponse);
    }*/

   @Test
    public void getIdTest() {
        RpcContext.getContext().setAttachment("clientCode", "yryzpc");
        TransactionFlowRecord record = new TransactionFlowRecord();
        //订单号
        record.setOrderId("yryz_new_test001");
        //支付单号
        record.setPaySn("yryz_new_paySn001");
        ////业务编码（外码）
        record.setBusiFCode(14717L);
        //总金额
        record.setTotalAmount(new BigDecimal(10000*10000));
        //总条数
        record.setTotalCount(2);

        //流水记录集合
        List<TransactionFlowRecord.Flow> flowList = new ArrayList<TransactionFlowRecord.Flow>();

        //现金
        TransactionFlowRecord.Flow flow = new TransactionFlowRecord.Flow();

        flow.setOwnerFCode(19052l);
        //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
        flow.setAccountTypeCode(1);
        //发生额(元*10000)
        flow.setAmount(new BigDecimal(10000*10000));
        //币种编码
        flow.setCurrencyCode(156l);
        //记账标识(10入账，20出帐)
        flow.setAccountingFlag(10);
        //现金标识(10现金，20非现金)
        flow.setCashFlag(10);
        //核算标识(10核算，20不核算)
        flow.setCheckFlag(10);
        flowList.add(flow);

        //收益
        TransactionFlowRecord.Flow flow2 = new TransactionFlowRecord.Flow();

        flow2.setOwnerFCode(19052l);
        //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
        flow2.setAccountTypeCode(3);
        //发生额(元*10000)
        flow2.setAmount(new BigDecimal(10000*10000));
        //币种编码
        flow2.setCurrencyCode(156l);
        //记账标识(10入账，20出帐)
        flow2.setAccountingFlag(10);
        //现金标识(10现金，20非现金)
        flow2.setCashFlag(20);
        //核算标识(10核算，20不核算)
        flow2.setCheckFlag(10);
        flowList.add(flow2);
        record.setFlowList(flowList);
        openTransactionApi.add(record);
    }







}
