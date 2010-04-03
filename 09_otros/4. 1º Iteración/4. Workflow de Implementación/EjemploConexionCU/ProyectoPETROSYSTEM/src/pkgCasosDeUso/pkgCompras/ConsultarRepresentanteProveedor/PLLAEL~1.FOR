<?xml version="1.0" encoding="UTF-8" ?>

<Form version="1.3" maxVersion="1.3" type="org.netbeans.modules.form.forminfo.JFrameFormInfo">
  <Properties>
    <Property name="defaultCloseOperation" type="int" value="2"/>
    <Property name="title" type="java.lang.String" value="Eliminar Representante de Proveedor"/>
    <Property name="resizable" type="boolean" value="false"/>
  </Properties>
  <SyntheticProperties>
    <SyntheticProperty name="formSizePolicy" type="int" value="1"/>
  </SyntheticProperties>
  <AuxValues>
    <AuxValue name="FormSettings_autoResourcing" type="java.lang.Integer" value="0"/>
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
              <EmptySpace max="32767" attributes="0"/>
              <Group type="103" groupAlignment="0" attributes="0">
                  <Group type="102" alignment="1" attributes="0">
                      <Component id="jPanel1" min="-2" max="-2" attributes="0"/>
                      <EmptySpace max="-2" attributes="0"/>
                  </Group>
                  <Group type="102" alignment="1" attributes="0">
                      <Component id="btnAceptar" min="-2" max="-2" attributes="0"/>
                      <EmptySpace min="-2" pref="21" max="-2" attributes="0"/>
                  </Group>
              </Group>
          </Group>
      </Group>
    </DimensionLayout>
    <DimensionLayout dim="1">
      <Group type="103" groupAlignment="0" attributes="0">
          <Group type="102" alignment="0" attributes="0">
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jPanel1" min="-2" max="-2" attributes="0"/>
              <EmptySpace min="-2" pref="15" max="-2" attributes="0"/>
              <Component id="btnAceptar" min="-2" max="-2" attributes="0"/>
              <EmptySpace max="32767" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
  </Layout>
  <SubComponents>
    <Container class="javax.swing.JPanel" name="jPanel1">
      <Properties>
        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
          <Border info="org.netbeans.modules.form.compat2.border.TitledBorderInfo">
            <TitledBorder title="Selecci&#xf3;n Representante Proveedor"/>
          </Border>
        </Property>
      </Properties>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace min="-2" max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="1" attributes="0">
                              <Component id="lblMensaje" alignment="1" min="-2" max="-2" attributes="0"/>
                              <Component id="jScrollPane2" alignment="1" min="-2" pref="338" max="-2" attributes="0"/>
                              <Group type="102" alignment="1" attributes="0">
                                  <Component id="lblApellido" min="-2" max="-2" attributes="0"/>
                                  <EmptySpace max="-2" attributes="0"/>
                                  <Component id="txtApellido" min="-2" pref="197" max="-2" attributes="0"/>
                                  <EmptySpace min="-2" pref="41" max="-2" attributes="0"/>
                                  <Component id="btnBuscar" min="-2" pref="93" max="-2" attributes="0"/>
                              </Group>
                          </Group>
                      </Group>
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace min="-2" pref="104" max="-2" attributes="0"/>
                          <Component id="btnEliminar" min="-2" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="39" max="-2" attributes="0"/>
                          <Component id="btnCancelar" min="-2" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                  <EmptySpace pref="14" max="32767" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="btnBuscar" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="lblApellido" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="txtApellido" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="23" max="-2" attributes="0"/>
                  <Component id="lblMensaje" min="-2" max="-2" attributes="0"/>
                  <EmptySpace max="-2" attributes="0"/>
                  <Component id="jScrollPane2" min="-2" pref="87" max="-2" attributes="0"/>
                  <EmptySpace pref="17" max="32767" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="btnEliminar" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="btnCancelar" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Component class="javax.swing.JLabel" name="lblApellido">
          <Properties>
            <Property name="text" type="java.lang.String" value="Apellido:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtApellido">
          <Events>
            <EventHandler event="keyPressed" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtApellidoKeyPressed"/>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtApellidoKeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JButton" name="btnBuscar">
          <Properties>
            <Property name="text" type="java.lang.String" value="Buscar"/>
          </Properties>
          <Events>
            <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnBuscarActionPerformed"/>
          </Events>
        </Component>
        <Container class="javax.swing.JScrollPane" name="jScrollPane2">
          <AuxValues>
            <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
          </AuxValues>

          <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
          <SubComponents>
            <Component class="javax.swing.JTable" name="tblRepresentantesDeProveedor">
              <Properties>
                <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
                  <Table columnCount="3" rowCount="4">
                    <Column editable="true" title="Nombre" type="java.lang.Object">
                      <Data value="Ana Cecilia"/>
                      <Data value="Diego"/>
                      <Data value="Fernando"/>
                      <Data value="Armando"/>
                    </Column>
                    <Column editable="true" title="Apellido" type="java.lang.Object">
                      <Data value="Andurno"/>
                      <Data value="Cohen"/>
                      <Data value="Bornoroni"/>
                      <Data value="Ayala"/>
                    </Column>
                    <Column editable="true" title="Tel&#xe9;fono" type="java.lang.Object">
                      <Data value="4703381"/>
                      <Data value="4552267"/>
                      <Data value="4880723"/>
                      <Data value="4558978"/>
                    </Column>
                  </Table>
                </Property>
              </Properties>
            </Component>
          </SubComponents>
        </Container>
        <Component class="javax.swing.JLabel" name="lblMensaje">
          <Properties>
            <Property name="text" type="java.lang.String" value="Seleccione el Representante que desea eliminar"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JButton" name="btnEliminar">
          <Properties>
            <Property name="text" type="java.lang.String" value="Eliminar"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JButton" name="btnCancelar">
          <Properties>
            <Property name="text" type="java.lang.String" value="Cancelar"/>
          </Properties>
          <Events>
            <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnCancelarActionPerformed"/>
          </Events>
        </Component>
      </SubComponents>
    </Container>
    <Component class="javax.swing.JButton" name="btnAceptar">
      <Properties>
        <Property name="text" type="java.lang.String" value="Aceptar"/>
      </Properties>
    </Component>
  </SubComponents>
</Form>
