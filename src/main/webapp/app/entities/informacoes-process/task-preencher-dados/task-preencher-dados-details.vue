<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('softwareQualityApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.nomeCompleto')">nomeCompleto</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nomeCompleto"
                id="informacoes-nomeCompleto"
                data-cy="nomeCompleto"
                v-model="taskContext.informacoesProcess.informacoes.nomeCompleto"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.email')">email</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="email"
                id="informacoes-email"
                data-cy="email"
                v-model="taskContext.informacoesProcess.informacoes.email"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.idade')">idade</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="idade"
                id="informacoes-idade"
                data-cy="idade"
                v-model="taskContext.informacoesProcess.informacoes.idade"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.cpf')">cpf</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cpf"
                id="informacoes-cpf"
                data-cy="cpf"
                v-model="taskContext.informacoesProcess.informacoes.cpf"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.cep')">cep</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cep"
                id="informacoes-cep"
                data-cy="cep"
                v-model="taskContext.informacoesProcess.informacoes.cep"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.estado')">estado</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="estado"
                id="informacoes-estado"
                data-cy="estado"
                v-model="taskContext.informacoesProcess.informacoes.estado"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.cidade')">cidade</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cidade"
                id="informacoes-cidade"
                data-cy="cidade"
                v-model="taskContext.informacoesProcess.informacoes.cidade"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.endereco')">endereco</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="endereco"
                id="informacoes-endereco"
                data-cy="endereco"
                v-model="taskContext.informacoesProcess.informacoes.endereco"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.complemento')">complemento</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="complemento"
                id="informacoes-complemento"
                data-cy="complemento"
                v-model="taskContext.informacoesProcess.informacoes.complemento"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.qtdVacinas')">qtdVacinas</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="qtdVacinas"
                id="informacoes-qtdVacinas"
                data-cy="qtdVacinas"
                v-model="taskContext.informacoesProcess.informacoes.qtdVacinas"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskPreencherDados.sintomas')">sintomas</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="sintomas"
                id="informacoes-sintomas"
                data-cy="sintomas"
                v-model="taskContext.informacoesProcess.informacoes.sintomas"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/InformacoesProcess/task/taskPreencherDados/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-preencher-dados-details.component.ts"></script>
