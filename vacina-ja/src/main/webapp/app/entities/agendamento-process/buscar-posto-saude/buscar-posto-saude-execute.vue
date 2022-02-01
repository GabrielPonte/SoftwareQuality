<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('jhipsterApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.buscarPostoSaude.cidade')" for="buscar-posto-saude-cidade"
                >Cidade</label
              >
              <input
                type="text"
                class="form-control"
                name="cidade"
                id="buscar-posto-saude-cidade"
                data-cy="cidade"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.cidade.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.cidade.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.cidade.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.buscarPostoSaude.estado')" for="buscar-posto-saude-estado"
                >Estado</label
              >
              <input
                type="text"
                class="form-control"
                name="estado"
                id="buscar-posto-saude-estado"
                data-cy="estado"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.estado.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.estado.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.estado.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.buscarPostoSaude.nomePosto')" for="buscar-posto-saude-nomePosto"
                >Nome Posto</label
              >
              <input
                type="text"
                class="form-control"
                name="nomePosto"
                id="buscar-posto-saude-nomePosto"
                data-cy="nomePosto"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.nomePosto.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.nomePosto.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.nomePosto.$model"
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

<script lang="ts" src="./buscar-posto-saude-execute.component.ts"></script>
