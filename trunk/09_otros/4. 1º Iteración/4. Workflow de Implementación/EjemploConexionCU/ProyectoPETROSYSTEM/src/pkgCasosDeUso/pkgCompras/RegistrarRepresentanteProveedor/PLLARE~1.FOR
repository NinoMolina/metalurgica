<?xml version="1.0" encoding="UTF-8" ?>

<Form version="1.3" maxVersion="1.3" type="org.netbeans.modules.form.forminfo.JDialogFormInfo">
  <Properties>
    <Property name="defaultCloseOperation" type="int" value="2"/>
    <Property name="title" type="java.lang.String" value="Registrar Representante de Proveedor"/>
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
              <Component id="jPanel1" max="32767" attributes="0"/>
              <EmptySpace max="-2" attributes="0"/>
          </Group>
          <Group type="102" alignment="1" attributes="0">
              <EmptySpace min="-2" pref="37" max="-2" attributes="0"/>
              <Component id="btnGrabar" min="-2" pref="109" max="-2" attributes="0"/>
              <EmptySpace pref="99" max="32767" attributes="0"/>
              <Component id="btnNuevo" min="-2" pref="92" max="-2" attributes="0"/>
              <EmptySpace min="-2" pref="35" max="-2" attributes="0"/>
              <Component id="btnCancelar" min="-2" pref="92" max="-2" attributes="0"/>
              <EmptySpace min="-2" pref="34" max="-2" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
    <DimensionLayout dim="1">
      <Group type="103" groupAlignment="0" attributes="0">
          <Group type="102" alignment="0" attributes="0">
              <EmptySpace max="-2" attributes="0"/>
              <Component id="jPanel1" min="-2" max="-2" attributes="0"/>
              <EmptySpace pref="26" max="32767" attributes="0"/>
              <Group type="103" groupAlignment="3" attributes="0">
                  <Component id="btnCancelar" alignment="3" min="-2" max="-2" attributes="0"/>
                  <Component id="btnGrabar" alignment="3" min="-2" max="-2" attributes="0"/>
                  <Component id="btnNuevo" alignment="3" min="-2" max="-2" attributes="0"/>
              </Group>
              <EmptySpace min="-2" pref="24" max="-2" attributes="0"/>
          </Group>
      </Group>
    </DimensionLayout>
  </Layout>
  <SubComponents>
    <Container class="javax.swing.JPanel" name="jPanel1">
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
                  <EmptySpace max="-2" attributes="0"/>
                  <Component id="jLabel1" min="-2" max="-2" attributes="0"/>
                  <EmptySpace min="-2" pref="23" max="-2" attributes="0"/>
                  <Component id="cmbProveedorAsociado" min="-2" pref="128" max="-2" attributes="1"/>
                  <EmptySpace pref="201" max="32767" attributes="0"/>
              </Group>
              <Group type="102" alignment="0" attributes="0">
                  <EmptySpace min="-2" pref="59" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="1" attributes="0">
                      <Component id="lblApellido" alignment="1" min="-2" max="-2" attributes="0"/>
                      <Component id="lblTelefono" alignment="1" min="-2" max="-2" attributes="0"/>
                      <Component id="lblNombre" alignment="1" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="23" pref="23" max="23" attributes="0"/>
                  <Group type="103" groupAlignment="0" max="-2" attributes="0">
                      <Component id="txtApellido" alignment="0" max="32767" attributes="1"/>
                      <Component id="txtNombre" alignment="0" pref="98" max="32767" attributes="1"/>
                      <Group type="102" alignment="1" attributes="0">
                          <Group type="103" groupAlignment="0" max="-2" attributes="0">
                              <Component id="txtCaracteristica3" min="0" pref="0" max="32767" attributes="1"/>
                              <Component id="txtCaracteristica2" min="0" pref="0" max="32767" attributes="1"/>
                              <Component id="txtCaracteristica1" alignment="0" pref="44" max="32767" attributes="1"/>
                          </Group>
                          <EmptySpace type="unrelated" max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="0" attributes="0">
                              <Component id="jLabel2" min="-2" pref="14" max="-2" attributes="0"/>
                              <Component id="jLabel3" min="-2" pref="14" max="-2" attributes="0"/>
                              <Component id="jLabel4" min="-2" pref="14" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="0" max="-2" attributes="0">
                              <Component id="txtTelefono3" alignment="1" max="32767" attributes="1"/>
                              <Component id="txtTelefono2" alignment="0" max="32767" attributes="1"/>
                              <Component id="txtTelefono1" alignment="0" pref="83" max="32767" attributes="1"/>
                          </Group>
                      </Group>
                  </Group>
                  <EmptySpace min="-2" pref="26" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Component id="cmbTelefono2" pref="99" max="32767" attributes="0"/>
                      <Component id="cmbTelefono3" alignment="0" pref="99" max="32767" attributes="0"/>
                      <Component id="cmbTelefono1" alignment="0" min="-2" pref="99" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="49" max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" attributes="0">
                  <EmptySpace max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="lblNombre" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="txtNombre" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="26" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="lblApellido" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="txtApellido" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="31" max="-2" attributes="0"/>
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Component id="lblTelefono" min="-2" max="-2" attributes="0"/>
                      <Group type="102" alignment="0" attributes="0">
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="cmbTelefono1" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="txtCaracteristica1" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="jLabel2" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="txtTelefono1" alignment="3" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="cmbTelefono2" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="txtTelefono2" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="txtCaracteristica2" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="jLabel3" alignment="3" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="cmbTelefono3" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="txtTelefono3" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="txtCaracteristica3" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="jLabel4" alignment="3" min="-2" max="-2" attributes="0"/>
                          </Group>
                      </Group>
                  </Group>
                  <EmptySpace pref="23" max="32767" attributes="0"/>
                  <Group type="103" groupAlignment="3" attributes="0">
                      <Component id="jLabel1" alignment="3" min="-2" max="-2" attributes="0"/>
                      <Component id="cmbProveedorAsociado" alignment="3" min="-2" max="-2" attributes="0"/>
                  </Group>
                  <EmptySpace min="-2" pref="23" max="-2" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Component class="javax.swing.JLabel" name="lblTelefono">
          <Properties>
            <Property name="text" type="java.lang.String" value="Tel&#xe9;fonos:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="lblApellido">
          <Properties>
            <Property name="text" type="java.lang.String" value="Apellido:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtApellido">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtApellidoKeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JTextField" name="txtNombre">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtNombreKeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JLabel" name="lblNombre">
          <Properties>
            <Property name="text" type="java.lang.String" value="Nombre:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtTelefono1">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtTelefono1KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JTextField" name="txtTelefono2">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtTelefono2KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JTextField" name="txtTelefono3">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtTelefono3KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbTelefono1">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="0"/>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="cmbTelefono1KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbTelefono2">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="0"/>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="cmbTelefono2KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbTelefono3">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="0"/>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="cmbTelefono3KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JTextField" name="txtCaracteristica1">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtCaracteristica1KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel2">
          <Properties>
            <Property name="text" type="java.lang.String" value="-" noResource="true"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JTextField" name="txtCaracteristica2">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtCaracteristica2KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JTextField" name="txtCaracteristica3">
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txtCaracteristica3KeyReleased"/>
          </Events>
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel3">
          <Properties>
            <Property name="text" type="java.lang.String" value="-" noResource="true"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel4">
          <Properties>
            <Property name="text" type="java.lang.String" value="-" noResource="true"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JLabel" name="jLabel1">
          <Properties>
            <Property name="text" type="java.lang.String" value="Proveedor Asociado:"/>
          </Properties>
        </Component>
        <Component class="javax.swing.JComboBox" name="cmbProveedorAsociado">
          <Properties>
            <Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor">
              <StringArray count="0"/>
            </Property>
          </Properties>
          <Events>
            <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="cmbProveedorAsociadoKeyReleased"/>
          </Events>
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
    <Component class="javax.swing.JButton" name="btnNuevo">
      <Properties>
        <Property name="text" type="java.lang.String" value="Nuevo"/>
      </Properties>
      <Events>
        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnNuevoActionPerformed"/>
      </Events>
    </Component>
    <Component class="javax.swing.JButton" name="btnGrabar">
      <Properties>
        <Property name="text" type="java.lang.String" value="Grabar"/>
      </Properties>
      <Events>
        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnGrabarActionPerformed"/>
        <EventHandler event="keyPressed" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="btnGrabarKeyPressed"/>
        <EventHandler event="keyReleased" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="btnGrabarKeyReleased"/>
      </Events>
    </Component>
  </SubComponents>
</Form>
