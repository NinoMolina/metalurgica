<?xml version="1.0" encoding="UTF-8" ?>

<Form version="1.3" maxVersion="1.5" type="org.netbeans.modules.form.forminfo.JFrameFormInfo">
  <NonVisualComponents>
    <Component class="javax.swing.JLabel" name="jLabel1">
      <Properties>
        <Property name="text" type="java.lang.String" value="jLabel1"/>
      </Properties>
    </Component>
  </NonVisualComponents>
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
          <Group type="102" alignment="0" attributes="0">
              <EmptySpace max="-2" attributes="0"/>
              <Group type="103" groupAlignment="1" max="-2" attributes="0">
                  <Component id="lblPedidodsDelProveedor" alignment="0" min="-2" max="-2" attributes="0"/>
                  <Component id="jScrollPaneProductos" alignment="0" max="32767" attributes="1"/>
                  <Component id="jScrollPanePedidos" alignment="0" pref="724" max="32767" attributes="1"/>
                  <Group type="102" alignment="1" attributes="1">
                      <Component id="btnRecibirTodo" min="-2" max="-2" attributes="0"/>
                      <EmptySpace max="-2" attributes="0"/>
                      <Component id="btnRecibirParcial" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <Component id="jPRecepcionCombustible" alignment="0" min="0" pref="724" max="32767" attributes="1"/>
              </Group>
              <EmptySpace pref="28" max="32767" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
    <DimensionLayout dim="1">
      <Group type="103" groupAlignment="0" attributes="0">
          <Group type="102" alignment="0" attributes="0">
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jPRecepcionCombustible" min="-2" max="-2" attributes="0"/>
              <EmptySpace max="-2" attributes="0"/>
              <Component id="lblPedidodsDelProveedor" min="-2" max="-2" attributes="0"/>
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jScrollPanePedidos" min="-2" pref="100" max="-2" attributes="0"/>
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jScrollPaneProductos" min="-2" pref="103" max="-2" attributes="0"/>
              <EmptySpace type="separate" max="-2" attributes="0"/>
              <Group type="103" groupAlignment="3" attributes="0">
                  <Component id="btnRecibirParcial" alignment="3" min="-2" max="-2" attributes="0"/>
                  <Component id="btnRecibirTodo" alignment="3" min="-2" max="-2" attributes="0"/>
              </Group>
              <EmptySpace pref="31" max="32767" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
  </Layout>
  <SubComponents>
    <Container class="javax.swing.JPanel" name="jPRecepcionCombustible">
      <Properties>
        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
          <Border info="org.netbeans.modules.form.compat2.border.TitledBorderInfo">
            <TitledBorder title="Recepci&#xf3;n Combustible"/>
          </Border>
        </Property>
      </Properties>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="1" attributes="0">
                  <EmptySpace min="-2" pref="40" max="-2" attributes="0"/>
                  <Component id="lblFecha" min="-2" pref="39" max="-2" attributes="1"/>
                  <EmptySpace type="separate" max="-2" attributes="0"/>
                  <Component id="jCdarChFecha" min="-2" pref="141" max="-2" attributes="0"/>
                  <EmptySpace min="-2" pref="73" max="-2" attributes="0"/>
                  <Component id="lblFecha1" min="-2" max="-2" attributes="1"/>
                  <EmptySpace type="unrelated" max="-2" attributes="0"/>
                  <Component id="jSpinFieldHora" min="-2" pref="36" max="-2" attributes="0"/>
                  <EmptySpace max="-2" attributes="0"/>
                  <Component id="jSpinFieldMinutos" min="-2" pref="37" max="-2" attributes="0"/>
                  <EmptySpace min="-2" pref="549" max="-2" attributes="0"/>
              </Group>
              <Group type="102" alignment="0" attributes="0">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" attributes="0">
                          <EmptySpace min="-2" pref="21" max="-2" attributes="0"/>
                          <Component id="lblProveedor" min="-2" max="-2" attributes="0"/>
                      </Group>
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="lblResponsable" min="-2" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="1" max="-2" attributes="0">
                      <Component id="cmbResponsable" alignment="0" max="32767" attributes="1"/>
                      <Component id="cmbProveedor" alignment="0" min="-2" pref="361" max="-2" attributes="1"/>
                  </Group>
                  <EmptySpace pref="536" max="32767" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Component id="jSpinFieldHora" alignment="1" max="32767" attributes="1"/>
                      <Component id="jSpinFieldMinutos" alignment="0" max="32767" attributes="1"/>
                      <Component id="lblFecha1" alignment="0" pref="20" max="32767" attributes="1"/>
                      <Component id="jCdarChFecha" max="32767" attributes="1"/>
                      <Component id="lblFecha" pref="20" max="32767" attributes="1"/>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="cmbResponsable" alignment="3" min="-2" max="-2" attributes="1"/>
                      <Component id="lblResponsable" alignment="3" max="32767" attributes="1"/>
                  </Group>
                  <EmptySpace min="-2" pref="8" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="cmbProveedor" alignment="3" min="-2" pref="20" max="-2" attributes="0"/>
                      <Component id="lblProveedor" alignment="3" min="-2" pref="17" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="11" max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Component class="com.toedter.calendar.JDateChooser" name="jCdarChFecha">
        </Component>
        <Component class="javax.swing.JLabel" name="lblFecha">
          <Properties>
            <Property name="text" type="java.lang.String" value="Fecha"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="lblResponsable">
          <Properties>
            <Property name="text" type="java.lang.String" value="Responsable"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbResponsable">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="1">
                <StringItem index="0" value="Seleccione Responsable"/>
              </StringArray>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="cmbResponsableActionPerformed"/>
          </Events>
        </Component>
        <Component class="javax.swing.JLabel" name="lblProveedor">
          <Properties>
            <Property name="text" type="java.lang.String" value="Proveedor"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbProveedor">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="1">
                <StringItem index="0" value="Seleccione Poveedor"/>
              </StringArray>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="itemStateChanged" listener="java.awt.event.ItemListener" parameters="java.awt.event.ItemEvent" handler="cmbProveedorItemStateChanged"/>
            <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="cmbProveedorActionPerformed"/>
          </Events>
        </Component>
        <Component class="com.toedter.components.JSpinField" name="jSpinFieldHora">
        </Component>
        <Component class="com.toedter.components.JSpinField" name="jSpinFieldMinutos">
        </Component>
        <Component class="javax.swing.JLabel" name="lblFecha1">
          <Properties>
            <Property name="text" type="java.lang.String" value="Hora"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="lblFecha2">
          <Properties>
            <Property name="text" type="java.lang.String" value="Hora"/>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
    <Container class="javax.swing.JScrollPane" name="jScrollPanePedidos">

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JTable" name="tblPedidosProveedor">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="3" rowCount="12">
                <Column editable="false" title="Pedido Numero" type="java.lang.Object">
                  <Data value=""/>
                  <Data value=""/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                </Column>
                <Column editable="false" title="Fecha" type="java.lang.Object">
                  <Data value=""/>
                  <Data value=""/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                </Column>
                <Column editable="false" title="Monto Total" type="java.lang.Object">
                  <Data value=""/>
                  <Data value=""/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                  <Data value="null"/>
                </Column>
              </Table>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="mouseClicked" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="tblPedidosProveedorMouseClicked"/>
            <EventHandler event="mouseReleased" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="tblPedidosProveedorMouseReleased"/>
          </Events>
        </Component>
      </SubComponents>
    </Container>
    <Component class="javax.swing.JLabel" name="lblPedidodsDelProveedor">
      <Properties>
        <Property name="text" type="java.lang.String" value="Pedidos del Proveedor"/>
      </Properties>
    </Component>
    <Container class="javax.swing.JScrollPane" name="jScrollPaneProductos">

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JTable" name="tblProductosPedido">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="4" rowCount="12">
                <Column editable="false" title="Estado de Recepci&#xf3;n" type="java.lang.Object"/>
                <Column editable="false" title="Producto" type="java.lang.Object"/>
                <Column editable="false" title="Cantidad Pedida" type="java.lang.Object"/>
                <Column editable="false" title="Cantidad Por Recibir" type="java.lang.Object"/>
              </Table>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
    <Component class="javax.swing.JButton" name="btnRecibirTodo">
      <Properties>
        <Property name="text" type="java.lang.String" value="Recibir Todo"/>
      </Properties>
      <Events>
        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnRecibirTodoActionPerformed"/>
      </Events>
    </Component>
    <Component class="javax.swing.JButton" name="btnRecibirParcial">
      <Properties>
        <Property name="text" type="java.lang.String" value="Recibir Parte"/>
      </Properties>
      <Events>
        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnRecibirParcialActionPerformed"/>
      </Events>
    </Component>
  </SubComponents>
</Form>
