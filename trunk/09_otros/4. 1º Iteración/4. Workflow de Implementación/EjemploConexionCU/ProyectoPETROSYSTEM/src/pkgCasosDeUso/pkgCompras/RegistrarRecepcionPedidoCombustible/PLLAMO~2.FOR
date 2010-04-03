<?xml version="1.0" encoding="UTF-8" ?>

<Form version="1.5" maxVersion="1.5" type="org.netbeans.modules.form.forminfo.JFrameFormInfo">
  <Properties>
    <Property name="defaultCloseOperation" type="int" value="3"/>
  </Properties>
  <SyntheticProperties>
    <SyntheticProperty name="formSizePolicy" type="int" value="1"/>
  </SyntheticProperties>
  <AuxValues>
    <AuxValue name="FormSettings_autoResourcing" type="java.lang.Integer" value="0"/>
    <AuxValue name="FormSettings_autoSetComponentName" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_generateMnemonicsCode" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_i18nAutoMode" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_layoutCodeTarget" type="java.lang.Integer" value="1"/>
    <AuxValue name="FormSettings_listenerGenerationStyle" type="java.lang.Integer" value="0"/>
    <AuxValue name="FormSettings_variablesLocal" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_variablesModifier" type="java.lang.Integer" value="2"/>
  </AuxValues>

  <Layout>
    <DimensionLayout dim="0">
      <Group type="103" groupAlignment="0" attributes="0">
          <Group type="102" attributes="0">
              <Group type="103" groupAlignment="0" attributes="0">
                  <Group type="102" alignment="0" attributes="0">
                      <EmptySpace min="-2" pref="22" max="-2" attributes="0"/>
                      <Component id="jPanel1" min="-2" max="-2" attributes="0"/>
                      <EmptySpace pref="124" max="32767" attributes="0"/>
                      <Component id="btnConfirmarRecepcion" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="0" attributes="0">
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="lblFormaPago" min="-2" max="-2" attributes="0"/>
                      <EmptySpace min="-2" pref="16" max="-2" attributes="0"/>
                      <Component id="rbtnContado" min="-2" max="-2" attributes="0"/>
                      <EmptySpace type="unrelated" max="-2" attributes="0"/>
                      <Component id="rbtnCuentaCorriente" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="0" attributes="0">
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="lblProveedor" min="-2" max="-2" attributes="0"/>
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="txtProveedor" min="-2" pref="105" max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="1" attributes="0">
                      <EmptySpace max="32767" attributes="0"/>
                      <Component id="jScrollPane2" min="-2" pref="590" max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="0" attributes="0">
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="lblRecibirTotal" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="0" attributes="0">
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="lblComprobantesRecibidos" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="0" attributes="0">
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="jScrollPane1" min="-2" pref="173" max="-2" attributes="0"/>
                  </Group>
              </Group>
              <EmptySpace max="-2" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
    <DimensionLayout dim="1">
      <Group type="103" groupAlignment="0" attributes="0">
          <Group type="102" attributes="0">
              <EmptySpace min="-2" pref="18" max="-2" attributes="0"/>
              <Group type="103" groupAlignment="3" attributes="0">
                  <Component id="lblProveedor" alignment="3" min="-2" max="-2" attributes="0"/>
                  <Component id="txtProveedor" alignment="3" min="-2" max="-2" attributes="0"/>
              </Group>
              <EmptySpace type="unrelated" max="-2" attributes="0"/>
              <Component id="lblRecibirTotal" min="-2" max="-2" attributes="0"/>
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jScrollPane2" min="-2" pref="103" max="-2" attributes="0"/>
              <EmptySpace max="-2" attributes="0"/>
              <Group type="103" groupAlignment="1" attributes="0">
                  <Group type="102" attributes="0">
                      <Component id="lblComprobantesRecibidos" min="-2" max="-2" attributes="0"/>
                      <EmptySpace min="-2" pref="4" max="-2" attributes="0"/>
                      <Component id="jScrollPane1" min="-2" pref="100" max="-2" attributes="0"/>
                      <EmptySpace type="unrelated" max="-2" attributes="0"/>
                      <Group type="103" groupAlignment="3" attributes="0">
                          <Component id="lblFormaPago" alignment="3" min="-2" max="-2" attributes="0"/>
                          <Component id="rbtnCuentaCorriente" alignment="3" min="-2" max="-2" attributes="0"/>
                          <Component id="rbtnContado" alignment="3" min="-2" max="-2" attributes="0"/>
                      </Group>
                      <EmptySpace min="-2" pref="6" max="-2" attributes="0"/>
                      <Component id="jPanel1" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <Component id="btnConfirmarRecepcion" min="-2" max="-2" attributes="0"/>
              </Group>
          </Group>
      </Group>
    </DimensionLayout>
  </Layout>
  <SubComponents>
    <Component class="javax.swing.JLabel" name="lblProveedor">
      <Properties>
        <Property name="text" type="java.lang.String" value="Proveedor:"/>
      </Properties>
    </Component>
    <Component class="javax.swing.JTextField" name="txtProveedor">
      <Properties>
        <Property name="editable" type="boolean" value="false"/>
      </Properties>
    </Component>
    <Container class="javax.swing.JScrollPane" name="jScrollPane2">

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JTable" name="tblCantidadesProductos">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="4" rowCount="4">
                <Column editable="false" title="Estado de Recepci&#xf3;n" type="java.lang.Boolean"/>
                <Column editable="false" title="Producto" type="java.lang.Object"/>
                <Column editable="false" title="Cantidad Pedida" type="java.lang.Object"/>
                <Column editable="true" title="Cantidad Por Recibir" type="java.lang.Object"/>
              </Table>
            </Property>
            <Property name="columnModel" type="javax.swing.table.TableColumnModel" editor="org.netbeans.modules.form.editors2.TableColumnModelEditor">
              <TableColumnModel selectionModel="0">
                <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                  <Title/>
                  <Editor/>
                  <Renderer/>
                </Column>
                <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                  <Title/>
                  <Editor/>
                  <Renderer/>
                </Column>
                <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                  <Title/>
                  <Editor/>
                  <Renderer/>
                </Column>
                <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                  <Title/>
                  <Editor/>
                  <Renderer/>
                </Column>
              </TableColumnModel>
            </Property>
            <Property name="tableHeader" type="javax.swing.table.JTableHeader" editor="org.netbeans.modules.form.editors2.JTableHeaderEditor">
              <TableHeader reorderingAllowed="true" resizingAllowed="true"/>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
    <Component class="javax.swing.JLabel" name="lblRecibirTotal">
      <Properties>
        <Property name="text" type="java.lang.String" value="Verifique las Cantidades Recibidas"/>
      </Properties>
    </Component>
    <Component class="javax.swing.JLabel" name="lblComprobantesRecibidos">
      <Properties>
        <Property name="text" type="java.lang.String" value="Comprobantes Recibidos"/>
      </Properties>
    </Component>
    <Component class="javax.swing.JRadioButton" name="rbtnCuentaCorriente">
      <Properties>
        <Property name="text" type="java.lang.String" value="Cuenta Corriente"/>
      </Properties>
    </Component>
    <Component class="javax.swing.JRadioButton" name="rbtnContado">
      <Properties>
        <Property name="selected" type="boolean" value="true"/>
        <Property name="text" type="java.lang.String" value="Contado"/>
      </Properties>
      <Events>
        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="rbtnContadoActionPerformed"/>
      </Events>
    </Component>
    <Component class="javax.swing.JLabel" name="lblFormaPago">
      <Properties>
        <Property name="text" type="java.lang.String" value="Forma de Pago:"/>
      </Properties>
    </Component>
    <Container class="javax.swing.JPanel" name="jPanel1">
      <Properties>
        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
          <Border info="org.netbeans.modules.form.compat2.border.TitledBorderInfo">
            <TitledBorder title="Plan de Pago"/>
          </Border>
        </Property>
      </Properties>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="1" attributes="0">
                      <Component id="jLabel3" alignment="1" min="-2" max="-2" attributes="0"/>
                      <Group type="103" alignment="1" groupAlignment="0" attributes="0">
                          <Group type="102" alignment="0" attributes="0">
                              <EmptySpace min="-2" pref="58" max="-2" attributes="0"/>
                              <Component id="jLabel4" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <Group type="102" alignment="0" attributes="0">
                              <EmptySpace max="-2" attributes="0"/>
                              <Component id="jLabel2" min="-2" max="-2" attributes="0"/>
                          </Group>
                      </Group>
                      <Component id="jLabel1" alignment="1" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Component id="cmbCuotas" alignment="0" min="-2" pref="41" max="-2" attributes="0"/>
                      <Component id="jCdarChvencimiento1" alignment="0" min="-2" max="-2" attributes="0"/>
                      <Component id="jCdarChvencimiento2" alignment="0" min="-2" max="-2" attributes="0"/>
                      <Component id="cmbInteres" alignment="0" min="-2" pref="53" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace pref="34" max="32767" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="cmbCuotas" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="jLabel1" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="1" attributes="0">
                      <Component id="jCdarChvencimiento1" alignment="1" min="-2" max="-2" attributes="0"/>
                      <Component id="jLabel2" alignment="1" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="1" attributes="0">
                      <Component id="jCdarChvencimiento2" alignment="1" min="-2" max="-2" attributes="0"/>
                      <Component id="jLabel3" alignment="1" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="cmbInteres" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="jLabel4" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Component class="javax.swing.JLabel" name="jLabel1">
          <Properties>
            <Property name="text" type="java.lang.String" value="Cantidad de Cuotas"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbCuotas">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="4">
                <StringItem index="0" value="1"/>
                <StringItem index="1" value="2"/>
                <StringItem index="2" value="3"/>
                <StringItem index="3" value="4"/>
              </StringArray>
            </Property>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel2">
          <Properties>
            <Property name="text" type="java.lang.String" value="Fecha Vencimiento Proxima Cuota"/>
          </Properties>
        </Component>
        <Component class="com.toedter.calendar.JDateChooser" name="jCdarChvencimiento1">
        </Component>
        <Component class="com.toedter.calendar.JDateChooser" name="jCdarChvencimiento2">
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel3">
          <Properties>
            <Property name="text" type="java.lang.String" value="Segunda Fecha Vencimiento "/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel4">
          <Properties>
            <Property name="text" type="java.lang.String" value="Inter&#xe9;s por Vencimiento"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbInteres">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="7">
                <StringItem index="0" value="0%"/>
                <StringItem index="1" value="1%"/>
                <StringItem index="2" value="2%"/>
                <StringItem index="3" value="3%"/>
                <StringItem index="4" value="4%"/>
                <StringItem index="5" value="5%"/>
                <StringItem index="6" value="6%"/>
              </StringArray>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
    <Component class="javax.swing.JButton" name="btnConfirmarRecepcion">
      <Properties>
        <Property name="text" type="java.lang.String" value="Confirmar Recepcion"/>
      </Properties>
    </Component>
    <Container class="javax.swing.JScrollPane" name="jScrollPane1">

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JTable" name="tblComprobantes">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="2" rowCount="4">
                <Column editable="true" title="Factura/s" type="java.lang.Object"/>
                <Column editable="true" title="Remito/s" type="java.lang.Object"/>
              </Table>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
  </SubComponents>
</Form>
