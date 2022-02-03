<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('softwareQualityApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.nomeCompleto')"
                for="task-preencher-dados-nomeCompleto"
                >Nome Completo</label
              >
              <input
                type="text"
                class="form-control"
                name="nomeCompleto"
                id="task-preencher-dados-nomeCompleto"
                readonly
                data-cy="nomeCompleto"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.nomeCompleto.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.nomeCompleto.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.nomeCompleto.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.email')" for="task-preencher-dados-email"
                >Email</label
              >
              <input
                type="text"
                class="form-control"
                name="email"
                id="task-preencher-dados-email"
                readonly
                data-cy="email"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.email.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.email.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.email.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.idade')" for="task-preencher-dados-idade"
                >Idade</label
              >
              <input
                type="number"
                class="form-control"
                name="idade"
                id="task-preencher-dados-idade"
                readonly
                data-cy="idade"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.idade.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.idade.$invalid,
                }"
                v-model.number="$v.taskContext.informacoesProcess.informacoes.idade.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.cpf')" for="task-preencher-dados-cpf"
                >Cpf</label
              >
              <input
                type="text"
                class="form-control"
                name="cpf"
                id="task-preencher-dados-cpf"
                data-cy="cpf"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.cpf.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.cpf.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.cpf.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.cep')" for="task-preencher-dados-cep"
                >Cep</label
              >
              <input
                type="text"
                class="form-control"
                name="cep"
                id="task-preencher-dados-cep"
                data-cy="cep"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.cep.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.cep.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.cep.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.estado')"
                for="task-preencher-dados-estado"
                >Estado</label
              >
              <input
                type="text"
                class="form-control"
                name="estado"
                id="task-preencher-dados-estado"
                data-cy="estado"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.estado.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.estado.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.estado.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.cidade')"
                for="task-preencher-dados-cidade"
                >Cidade</label
              >
              <input
                type="text"
                class="form-control"
                name="cidade"
                id="task-preencher-dados-cidade"
                data-cy="cidade"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.cidade.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.cidade.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.cidade.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.endereco')"
                for="task-preencher-dados-endereco"
                >Endereco</label
              >
              <input
                type="text"
                class="form-control"
                name="endereco"
                id="task-preencher-dados-endereco"
                data-cy="endereco"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.endereco.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.endereco.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.endereco.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.complemento')"
                for="task-preencher-dados-complemento"
                >Complemento</label
              >
              <input
                type="text"
                class="form-control"
                name="complemento"
                id="task-preencher-dados-complemento"
                data-cy="complemento"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.complemento.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.complemento.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.complemento.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.qtdVacinas')"
                for="task-preencher-dados-qtdVacinas"
                >Qtd Vacinas</label
              >
              <input
                type="number"
                class="form-control"
                name="qtdVacinas"
                id="task-preencher-dados-qtdVacinas"
                data-cy="qtdVacinas"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.qtdVacinas.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.qtdVacinas.$invalid,
                }"
                v-model.number="$v.taskContext.informacoesProcess.informacoes.qtdVacinas.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskPreencherDados.sintomas')"
                for="task-preencher-dados-sintomas"
                >Sintomas</label
              >
              <input
                type="text"
                class="form-control"
                name="sintomas"
                id="task-preencher-dados-sintomas"
                data-cy="sintomas"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.sintomas.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.sintomas.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.sintomas.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-preencher-dados-execute.component.ts"></script>
