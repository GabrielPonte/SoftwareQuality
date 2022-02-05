/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TipoInformacaoComponent from '@/entities/tipo-informacao/tipo-informacao.vue';
import TipoInformacaoClass from '@/entities/tipo-informacao/tipo-informacao.component';
import TipoInformacaoService from '@/entities/tipo-informacao/tipo-informacao.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('TipoInformacao Management Component', () => {
    let wrapper: Wrapper<TipoInformacaoClass>;
    let comp: TipoInformacaoClass;
    let tipoInformacaoServiceStub: SinonStubbedInstance<TipoInformacaoService>;

    beforeEach(() => {
      tipoInformacaoServiceStub = sinon.createStubInstance<TipoInformacaoService>(TipoInformacaoService);
      tipoInformacaoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TipoInformacaoClass>(TipoInformacaoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tipoInformacaoService: () => tipoInformacaoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tipoInformacaoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTipoInformacaos();
      await comp.$nextTick();

      // THEN
      expect(tipoInformacaoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tipoInformacaos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tipoInformacaoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeTipoInformacao();
      await comp.$nextTick();

      // THEN
      expect(tipoInformacaoServiceStub.delete.called).toBeTruthy();
      expect(tipoInformacaoServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
