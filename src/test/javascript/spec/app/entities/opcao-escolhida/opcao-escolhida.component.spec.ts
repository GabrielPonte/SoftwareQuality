/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import OpcaoEscolhidaComponent from '@/entities/opcao-escolhida/opcao-escolhida.vue';
import OpcaoEscolhidaClass from '@/entities/opcao-escolhida/opcao-escolhida.component';
import OpcaoEscolhidaService from '@/entities/opcao-escolhida/opcao-escolhida.service';

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
  describe('OpcaoEscolhida Management Component', () => {
    let wrapper: Wrapper<OpcaoEscolhidaClass>;
    let comp: OpcaoEscolhidaClass;
    let opcaoEscolhidaServiceStub: SinonStubbedInstance<OpcaoEscolhidaService>;

    beforeEach(() => {
      opcaoEscolhidaServiceStub = sinon.createStubInstance<OpcaoEscolhidaService>(OpcaoEscolhidaService);
      opcaoEscolhidaServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<OpcaoEscolhidaClass>(OpcaoEscolhidaComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          opcaoEscolhidaService: () => opcaoEscolhidaServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      opcaoEscolhidaServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllOpcaoEscolhidas();
      await comp.$nextTick();

      // THEN
      expect(opcaoEscolhidaServiceStub.retrieve.called).toBeTruthy();
      expect(comp.opcaoEscolhidas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      opcaoEscolhidaServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeOpcaoEscolhida();
      await comp.$nextTick();

      // THEN
      expect(opcaoEscolhidaServiceStub.delete.called).toBeTruthy();
      expect(opcaoEscolhidaServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
