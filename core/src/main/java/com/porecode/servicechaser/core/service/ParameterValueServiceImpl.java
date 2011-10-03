package com.porecode.servicechaser.core.service;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.porecode.rpc.protobuf.CoreServices;
import com.porecode.rpc.protobuf.EntityProtos;
import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import com.porecode.servicechaser.core.protobuf.mapper.ReflectiveMapper;

import javax.inject.Inject;
import java.util.List;

/**
 * Protobuffer {@link CoreServices.ParameterValueService} implementation
 *
 * @author filipovskii_off
 */
public final class ParameterValueServiceImpl
    extends CoreServices.ParameterValueService {

  private ParameterValueDao dao;

  @Inject
  public ParameterValueServiceImpl(ParameterValueDao dao) {
    this.dao = dao;
  }

  private ParameterValueServiceImpl() {

  }

  /**
   * List all {@link ParameterValue}s from database
   *
   * @param controller
   * @param request
   * @param done
   */
  @Override
  public void listAll(
      RpcController controller,
      CoreServices.ParameterValueRequest request,
      RpcCallback<CoreServices.ParameterValueResponse> done) {

    List<ParameterValue> daoResult = dao.selectAll();
    CoreServices.ParameterValueResponse.Builder resBuilder =
        CoreServices.ParameterValueResponse.newBuilder();

    for (ParameterValue daoValue : daoResult) {
      resBuilder.addParameterValues(
          ReflectiveMapper.toMessageBuilder(
              daoValue,
              EntityProtos.ParameterValue.newBuilder()));
    }
    done.run(resBuilder.build());
  }
}
