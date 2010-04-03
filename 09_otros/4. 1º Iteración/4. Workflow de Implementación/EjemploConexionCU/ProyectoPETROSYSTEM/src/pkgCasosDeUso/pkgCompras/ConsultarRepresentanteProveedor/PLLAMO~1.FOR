<?xml version="1.0" encoding="UTF-8" ?>

<Form version="1.3" maxVersion="1.3" type="org.netbeans.modules.form.forminfo.JFrameFormInfo">
  <NonVisualComponents>
    <Container class="javax.swing.JScrollPane" name="jScrollPane2">
      <AuxValues>
        <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
      </AuxValues>

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JTable" name="jTable2">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="4" rowCount="4">
                <Column editable="true" title="Title 1" type="java.lang.Object"/>
                <Column editable="true" title="Title 2" type="java.lang.Object"/>
                <Column editable="true" title="Title 3" type="java.lang.Object"/>
                <Column editable="true" title="Title 4" type="java.lang.Object"/>
              </Table>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
    <Container class="javax.swing.JScrollPane" name="jScrollPane3">
      <AuxValues>
        <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
      </AuxValues>

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JTable" name="jTable3">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="4" rowCount="4">
                <Column editable="true" title="Title 1" type="java.lang.Object"/>
                <Column editable="true" title="Title 2" type="java.lang.Object"/>
                <Column editable="true" title="Title 3" type="java.lang.Object"/>
                <Column editable="true" title="Title 4" type="java.lang.Object"/>
              </Table>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
  </NonVisualComponents>
  <Properties>
    <Property name="defaultCloseOperation" type="int" value="2"/>
    <Property name="title" type="java.lang.String" value="Modificar Representante de Proveedor"/>
    <Property name="resizable" type="boolean" value="false"/>
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
              <EmptySpace max="-2" attributes="0"/>
              <Group type="103" groupAlignment="1" attributes="0">
                  <Component id="jPanel2" alignment="1" max="32767" attributes="0"/>
                  <Component id="jPanel1" alignment="0" min="-2" max="-2" attributes="0"/>
              </Group>
              <EmptySpace min="-2" pref="23" max="-2" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
    <DimensionLayout dim="1">
      <Group type="103" groupAlignment="0" attributes="0">
          <Group type="102" alignment="0" attributes="0">
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jPanel1" min="-2" max="-2" attributes="0"/>
              <EmptySpace min="-2" pref="23" max="-2" attributes="0"/>
              <Component id="jPanel2" min="-2" max="-2" attributes="0"/>
              <EmptySpace min="-2" pref="59" max="-2" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
  </Layout>
  <SubComponents>
    <Container class="javax.swing.JPanel" name="jPanel1">
      <Properties>
        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
          <Border info="org.netbeans.modules.form.compat2.border.TitledBorderInfo">
            <TitledBorder title="Selecci&#xf3;n Representante Proveeedor"/>
          </Border>
        </Property>
      </Properties>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" attributes="0">
                          <Component id="lblApellido1" min="-2" max="-2" attributes="0"/>
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="txtApellido1" min="-2" pref="197" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="18" max="-2" attributes="0"/>
                          <Component id="btnBuscar" min="-2" pref="93" max="-2" attributes="0"/>
                      </Group>
                      <Group type="102" alignment="1" attributes="0">
                          <EmptySpace max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="0" attributes="0">
                              <Group type="102" alignment="1" attributes="0">
                                  <EmptySpace pref="150" max="-2" attributes="0"/>
                                  <Component id="lblMensaje" min="-2" max="-2" attributes="0"/>
                              </Group>
                              <Component id="jScrollPane1" alignment="0" pref="385" max="32767" attributes="0"/>
                          </Group>
                      </Group>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="lblApellido1" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="txtApellido1" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="btnBuscar" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace pref="27" max="32767" attributes="0"/>
                  <Component id="lblMensaje" min="-2" max="-2" attributes="0"/>
                  <EmptySpace max="-2" attributes="0"/>
                  <Component id="jScrollPane1" min="-2" pref="89" max="-2" attributes="0"/>
                  <EmptySpace max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Component class="javax.swing.JLabel" name="lblMensaje">
          <Properties>
            <Property name="text" type="java.lang.String" value="Seleccione el Representante que desea modificar"/>
          </Properties>
        </Component>
        <Container class="javax.swing.JScrollPane" name="jScrollPane1">
          <AuxValues>
            <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
          </AuxValues>

          <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
          <SubComponents>
            <Component class="javax.swing.JTable" name="tblRepresentantesDeProveedor">
              <Properties>
                <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
                  <Table columnCount="3" rowCount="4">
                    <Column editable="true" title="Title 1" type="java.lang.Object"/>
                    <Column editable="true" title="Title 2" type="java.lang.Object"/>
                    <Column editable="true" title="Title 3" type="java.lang.Object"/>
                  </Table>
                </Property>
              </Properties>
            </Component>
          </SubComponents>
        </Container>
        <Component class="javax.swing.JLabel" name="lblApellido1">
          <Properties>
            <Property name="text" type="java.lang.String" value="Apellido:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtApellido1">
          <Events>
            <EventHandler event="keyPressed" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtApellido1KeyPressed"/>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtApellido1KeyReleased"/>
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
      </SubComponents>
    </Container>
    <Container class="javax.swing.JPanel" name="jPanel2">
      <Properties>
        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
          <Border info="org.netbeans.modules.form.compat2.border.TitledBorderInfo">
            <TitledBorder title="Datos Representante Proveedor"/>
          </Border>
        </Property>
      </Properties>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" attributes="0">
                          <EmptySpace min="-2" pref="84" max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="1" attributes="0">
                              <Component id="lblNombre" alignment="1" min="-2" max="-2" attributes="0"/>
                              <Component id="lblApellido" alignment="1" min="-2" max="-2" attributes="0"/>
                              <Component id="lblTelefono" alignment="1" min="-2" max="-2" attributes="0"/>
                          </Group>
                      </Group>
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace min="-2" pref="32" max="-2" attributes="0"/>
                          <Component id="btnModificar" min="-2" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" attributes="0">
                          <EmptySpace min="-2" pref="29" max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="0" attributes="0">
                              <Component id="txtApellido" alignment="0" pref="147" max="32767" attributes="1"/>
                              <Component id="txtNombre" alignment="0" min="-2" pref="122" max="-2" attributes="1"/>
                              <Component id="jScrollPane4" alignment="0" min="0" pref="0" max="32767" attributes="1"/>
                          </Group>
                      </Group>
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace min="-2" pref="49" max="-2" attributes="0"/>
                          <Component id="btnEliminar" min="-2" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                  <EmptySpace max="-2" attributes="0"/>
                  <Component id="btnCancelar" min="-2" max="-2" attributes="0"/>
                  <EmptySpace min="-2" pref="32" max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="txtNombre" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="lblNombre" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="17" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="txtApellido" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="lblApellido" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="17" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Component id="lblTelefono" min="-2" max="-2" attributes="0"/>
                      <Component id="jScrollPane4" min="-2" pref="87" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="24" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="btnCancelar" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="btnEliminar" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="btnModificar" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace max="32767" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Component class="javax.swing.JLabel" name="lblNombre">
          <Properties>
            <Property name="text" type="java.lang.String" value="Nombre:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtNombre">
          <Properties>
            <Property name="text" type="java.lang.String" value="Diego"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="lblApellido">
          <Properties>
            <Property name="text" type="java.lang.String" value="Apellido:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtApellido">
          <Properties>
            <Property name="text" type="java.lang.String" value="Cohen"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="lblTelefono">
          <Properties>
            <Property name="text" type="java.lang.String" value="Tel&#xe9;fonos:"/>
          </Properties>
        </Component>
        <Container class="javax.swing.JScrollPane" name="jScrollPane4">
          <AuxValues>
            <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
          </AuxValues>

          <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
          <SubComponents>
            <Component class="javax.swing.JTable" name="tblTelefonos">
              <Properties>
                <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
                  <Table columnCount="1" rowCount="4">
                    <Column editable="true" title="Tel&#xe9;fonos" type="java.lang.Object">
                      <Data value="4552267"/>
                      <Data value="null"/>
                      <Data value="null"/>
                      <Data value="null"/>
                    </Column>
                  </Table>
                </Property>
              </Properties>
              <AuxValues>
                <AuxValue name="JavaCodeGenerator_SerializeTo" type="java.lang.String" value="PllaModificarRepresentanteProveedor_tblTelefonos"/>
              </AuxValues>
            </Component>
          </SubComponents>
        </Container>
        <Component class="javax.swing.JButton" name="btnCancelar">
          <Properties>
            <Property name="text" type="java.lang.String" value="Cancelar"/>
          </Properties>
          <Events>
            <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnCancelarActionPerformed"/>
          </Events>
        </Component>
        <Component class="javax.swing.JButton" name="btnModificar">
          <Properties>
            <Property name="text" type="java.lang.String" value="Modificar"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JButton" name="btnEliminar">
          <Properties>
            <Property name="text" type="java.lang.String" value="Eliminar"/>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
  </SubComponents>
</Form>
