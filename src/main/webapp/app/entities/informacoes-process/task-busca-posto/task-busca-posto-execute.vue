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
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskBuscaPosto.cidade')" for="task-busca-posto-cidade"
                >Cidade</label
              >
              <input
                type="text"
                class="form-control"
                name="cidade"
                id="task-busca-posto-cidade"
                data-cy="cidade"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.cidade.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.cidade.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.cidade.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskBuscaPosto.estado')" for="task-busca-posto-estado"
                >Estado</label
              >
              <input
                type="text"
                class="form-control"
                name="estado"
                id="task-busca-posto-estado"
                data-cy="estado"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.estado.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.estado.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.estado.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskBuscaPosto.nomePosto')" for="task-busca-posto-nomePosto"
                >Nome Posto</label
              >
              <input
                type="text"
                class="form-control"
                name="nomePosto"
                id="task-busca-posto-nomePosto"
                data-cy="nomePosto"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.nomePosto.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.nomePosto.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.nomePosto.$model"
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

<script lang="ts" src="./task-busca-posto-execute.component.ts"></script>
