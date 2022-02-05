<template>
  <div>
    <h2 id="page-heading" data-cy="TipoInformacaoHeading">
      <span v-text="$t('softwareQualityApp.tipoInformacao.home.title')" id="tipo-informacao-heading">Tipo Informacaos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('softwareQualityApp.tipoInformacao.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TipoInformacaoCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-tipo-informacao"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('softwareQualityApp.tipoInformacao.home.createLabel')"> Create a new Tipo Informacao </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tipoInformacaos && tipoInformacaos.length === 0">
      <span v-text="$t('softwareQualityApp.tipoInformacao.home.notFound')">No tipoInformacaos found</span>
    </div>
    <div class="table-responsive" v-if="tipoInformacaos && tipoInformacaos.length > 0">
      <table class="table table-striped" aria-describedby="tipoInformacaos">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.tipoInformacao.tipoInfo')">Tipo Info</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tipoInformacao in tipoInformacaos" :key="tipoInformacao.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TipoInformacaoView', params: { tipoInformacaoId: tipoInformacao.id } }">{{
                tipoInformacao.id
              }}</router-link>
            </td>
            <td>{{ tipoInformacao.tipoInfo }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TipoInformacaoView', params: { tipoInformacaoId: tipoInformacao.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TipoInformacaoEdit', params: { tipoInformacaoId: tipoInformacao.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tipoInformacao)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="softwareQualityApp.tipoInformacao.delete.question"
          data-cy="tipoInformacaoDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tipoInformacao-heading" v-text="$t('softwareQualityApp.tipoInformacao.delete.question', { id: removeId })">
          Are you sure you want to delete this Tipo Informacao?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tipoInformacao"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTipoInformacao()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./tipo-informacao.component.ts"></script>
