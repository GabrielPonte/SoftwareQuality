/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LocalDesejadoComponent from '@/entities/local-desejado/local-desejado.vue';
import LocalDesejadoClass from '@/entities/local-desejado/local-desejado.component';
import LocalDesejadoService from '@/entities/local-desejado/local-desejado.service';

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
  describe('LocalDesejado Management Component', () => {
    let wrapper: Wrapper<LocalDesejadoClass>;
    let comp: LocalDesejadoClass;
    let localDesejadoServiceStub: SinonStubbedInstance<LocalDesejadoService>;

    beforeEach(() => {
      localDesejadoServiceStub = sinon.createStubInstance<LocalDesejadoService>(LocalDesejadoService);
      localDesejadoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LocalDesejadoClass>(LocalDesejadoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          localDesejadoService: () => localDesejadoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      localDesejadoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLocalDesejados();
      await comp.$nextTick();

      // THEN
      expect(localDesejadoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.localDesejados[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      localDesejadoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeLocalDesejado();
      await comp.$nextTick();

      // THEN
      expect(localDesejadoServiceStub.delete.called).toBeTruthy();
      expect(localDesejadoServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
